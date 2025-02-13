package cga.api

interface CgaRootType<Type: CgaRootType<Type, *>, Config: CgaRootConfiguration<*, Type>> : CgaType<Type, Config> {
}