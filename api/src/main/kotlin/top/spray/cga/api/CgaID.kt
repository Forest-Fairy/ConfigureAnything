package top.spray.cga.api

import java.io.Serializable
import java.util.*

interface CgaID: Serializable {
    fun get(): String

    // 静态方法
    companion object {
        fun make(id: String): CgaID = CgaID.CgaIDImpl(id)
        fun generate(): CgaID = CgaID.CgaIDImpl(UUID.randomUUID().toString())
    }
    private class CgaIDImpl (val id: String): CgaID {
        override fun get(): String = id
    }
}
