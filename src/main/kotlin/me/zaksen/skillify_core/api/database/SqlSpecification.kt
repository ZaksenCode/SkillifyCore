package me.zaksen.skillify_core.api.database

fun interface SqlSpecification {
    fun toSqlClauses(): String
}