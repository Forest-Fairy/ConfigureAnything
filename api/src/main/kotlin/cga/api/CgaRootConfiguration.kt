package cga.api

interface CgaRootConfiguration<Value: CgaRootValue<*>, Type: CgaRootType<Type, *>>: CgaConfiguration<Value, Type> {
}