package cga.api

import java.io.Serializable

interface CgaID: Serializable {
    fun get(): String;
}