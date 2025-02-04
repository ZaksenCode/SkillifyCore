package me.zaksen.skillify_core.api.database

fun interface RepositorySpecification<T> {
    fun specified(value: T): Boolean
}