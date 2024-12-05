package top.forestfairy.cga.nginx

import top.forestfairy.cga.*
import java.util.LinkedList

interface CgaNginxConfiguration<Value: CgaValue<*>>: CgaConfiguration<Value, CgaNginxType> {
    override fun Type(): CgaNginxType
    override fun check(valueCheck: Boolean): CgaNginxCheckResult {
        val check = CgaNginxCheckResult(0, CgaCheckResult.OK)
        if (valueCheck && this.Value() is CgaValueList<*>) {
            for (son in this.Value() as CgaValueList<*>) {
                if (son is CgaNginxConfiguration<*>) {
                    check.children.add(son.check(true))
                }
            }
        }
        return check
    }
}
interface CgaNginxType: CgaType<CgaNginxType, CgaNginxConfiguration<*>> {
    override fun checkAvailableCount(config: CgaNginxConfiguration<*>): Int = -1
}

open class CgaNginxCheckResult(val status: Int, val message: String): CgaCheckResult {
    var children: LinkedList<CgaNginxCheckResult> = LinkedList<CgaNginxCheckResult>()
    override fun status(): Int = status
    override fun getMessage(): String = message
    override fun getChildrenNodeCheckResults(): Collection<CgaNginxCheckResult> = children
}
open class CgaNginxRootConfiguration: CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>> {
    lateinit var lastVersionID: CgaID
    lateinit var parentId: CgaID
    lateinit var id: CgaID
    var sortNum: Int = 0
    lateinit var name: String
    lateinit var desc: String
    lateinit var value: CgaValueList<CgaNginxConfiguration<*>>
    override fun check(valueCheck: Boolean): CgaNginxCheckResult {
        val check = CgaNginxCheckResult(0, CgaCheckResult.OK)
        if (valueCheck) {
            for (son in value) {
                check.children.add(son.check(true))
            }
        }
        return check
    }
    override fun getLastVersionId(): CgaID = lastVersionID
    override fun ParentId(): CgaID = parentId
    override fun ID(): CgaID = id
    override fun SortNum(): Int = sortNum
    override fun Name(): String = name
    override fun Description(): String = desc
    override fun Type() = CgaNginxStreamType
    override fun Value(): CgaValueList<CgaNginxConfiguration<*>> = value

}