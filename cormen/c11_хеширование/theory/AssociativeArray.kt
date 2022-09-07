package cormen.c11_хеширование.theory

interface AssociativeArray<KEY, VALUE>{
    fun insert(key:KEY, value: VALUE)
    fun delete(key:KEY)
    fun get(key:KEY): VALUE?
}