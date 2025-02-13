package top.forestfairy.top.spray.cga.nginx.base

import top.forestfairy.cga.nginx.api.CgaNginxType
import java.util.*

interface CgaNginxRegistry<Receive> {
    fun registry(type: Receive): Boolean
    fun all(): List<Receive>


    object NginxTypes: CgaNginxRegistry<CgaNginxType> {
        private val list: LinkedList<CgaNginxType> = LinkedList<CgaNginxType>()

        override fun registry(type: CgaNginxType) = list.add(type)
        override fun all(): List<CgaNginxType> = list
    }

}

