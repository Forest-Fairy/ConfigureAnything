package top.spray.cga.api.value

import top.spray.cga.api.CgaValue
import java.util.*
import java.util.stream.Stream

class CgaValueList<Value: CgaValue<*>>(private val children: Collection<Value>, private val desc: String)
    : CgaValue<Collection<Value>>, Collection<Value> {
    override fun get(): Collection<Value> = this.children
    override fun desc(): String = this.desc

    override val size: Int get() = get().size
    override fun contains(element: Value): Boolean = get().contains(element)
    override fun containsAll(elements: Collection<Value>): Boolean = get().containsAll(elements)
    override fun isEmpty(): Boolean = get().isEmpty()
    override fun iterator(): Iterator<Value> = get().iterator()
    override fun parallelStream(): Stream<Value> = get().parallelStream()
    override fun spliterator(): Spliterator<Value> = get().spliterator()
    override fun stream(): Stream<Value> = get().stream()
}