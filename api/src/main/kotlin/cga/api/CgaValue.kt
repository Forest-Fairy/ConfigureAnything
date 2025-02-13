package cga.api

interface CgaValue<V> {
    fun get(): V;
    fun desc(): String;
}