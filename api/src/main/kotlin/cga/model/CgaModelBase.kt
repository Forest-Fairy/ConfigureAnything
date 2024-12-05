package top.forestfairy.cga.model

import java.io.Serializable
import java.util.*
import java.util.function.IntFunction
import java.util.stream.Stream
import kotlin.Comparator

interface CgaEntity {
    fun getId(): CgaID
}
interface CgaVersionedEntity: CgaEntity {
    fun getLastVersionId(): CgaID;
}
interface CgaRichModel<Entity: CgaRichModel<Entity>>: CgaEntity {
    /**
     * it means upsert with self when the other is null
     */
    fun upsert(other: Entity?): Entity;
    fun delete(): Long
}
interface CgaConfiguration<Value: CgaValue<*>>: Comparable<CgaConfiguration<*>>, CgaVersionedEntity {
    override fun getId(): CgaID;
    fun getParentId(): CgaID;
    fun getSort(): Long;
    fun getName(): String;
    fun getDesc(): String;
    fun getType(): CgaType;
    fun getValue(): Value;
    override fun getLastVersionId(): CgaID;
    override fun compareTo(other: CgaConfiguration<*>): Int {
        return Comparator.comparingLong<CgaConfiguration<*>> { it.getSort() }.compare(this, other)
    }
}
interface CgaID: Serializable {
    fun get(): String;
}
interface CgaType {
    fun get(): String;
    fun desc(): String;
}
interface CgaValue<V> {
    fun get(): V;
    fun desc(): String;
}
interface CgaValueText: CgaValue<String> {
}
interface CgaValueList<CgaConfig: CgaConfiguration<*>>: CgaValue<Collection<CgaConfig>>, Collection<CgaConfig> {
    fun children(): Collection<CgaConfig>
    override fun get(): Collection<CgaConfig> = children()
    override val size: Int get() = children().size
    override fun contains(element: CgaConfig): Boolean = children().contains(element)
    override fun containsAll(elements: Collection<CgaConfig>): Boolean = children().containsAll(elements)
    override fun isEmpty(): Boolean = children().isEmpty()
    override fun iterator(): Iterator<CgaConfig> = children().iterator()
    override fun parallelStream(): Stream<CgaConfig> = children().parallelStream()
    override fun spliterator(): Spliterator<CgaConfig> = children().spliterator()
    override fun stream(): Stream<CgaConfig> = children().stream()
    override fun <T : Any?> toArray(generator: IntFunction<Array<T>>?): Array<T> = children().toArray(generator)
}
