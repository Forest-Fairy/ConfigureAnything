package cga.api.value

import cga.api.CgaConfiguration
import cga.api.CgaValue
import java.util.*
import java.util.stream.Stream

class CgaValueList<CgaConfig: CgaConfiguration<*, *>>(private val children: Collection<CgaConfig>, private val desc: String)
    : CgaValue<Collection<CgaConfig>>, Collection<CgaConfig> {
    override fun get(): Collection<CgaConfig> = this.children
    override fun desc(): String = this.desc

    override val size: Int get() = get().size
    override fun contains(element: CgaConfig): Boolean = get().contains(element)
    override fun containsAll(elements: Collection<CgaConfig>): Boolean = get().containsAll(elements)
    override fun isEmpty(): Boolean = get().isEmpty()
    override fun iterator(): Iterator<CgaConfig> = get().iterator()
    override fun parallelStream(): Stream<CgaConfig> = get().parallelStream()
    override fun spliterator(): Spliterator<CgaConfig> = get().spliterator()
    override fun stream(): Stream<CgaConfig> = get().stream()
}