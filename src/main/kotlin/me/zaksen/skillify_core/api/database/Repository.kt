package me.zaksen.skillify_core.api.database

interface Repository<T, K> {
    fun add(value: T)
    fun remove(value: T)
    fun update(value: T)
    fun query(specification: K): Set<T>
}