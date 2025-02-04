package me.zaksen.skillify_core.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CoreConfig {
    @SerialName("database_connection")
    val mysql: Mysql = Mysql()
}

@Serializable
@SerialName("mysql")
class Mysql {
    @SerialName("host")
    val host: String = "localhost"

    @SerialName("port")
    val port: String = "2222"

    @SerialName("base")
    val base: String = "test"

    @SerialName("table")
    val table: String = "table"

    @SerialName("user")
    val user: String = "root"

    @SerialName("pass")
    val pass: String = "root"
}