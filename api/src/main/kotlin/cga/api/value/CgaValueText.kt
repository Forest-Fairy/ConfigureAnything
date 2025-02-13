package cga.api.value

import cga.api.CgaValue

class CgaValueText(private val text: String, private val desc: String): CgaValue<String> {
    override fun get(): String = this.text

    override fun desc(): String = this.desc
}