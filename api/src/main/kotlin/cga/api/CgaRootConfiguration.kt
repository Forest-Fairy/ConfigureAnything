package cga.api

interface CgaRootConfiguration<Value: CgaValue<*>, Type: CgaRootType<Type, *>>: CgaConfiguration<Value, Type> {
}