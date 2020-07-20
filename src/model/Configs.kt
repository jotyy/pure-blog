package top.jotyy.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object Configs : Table("tb_config") {
    val name = varchar("config_name", 100)
    val value = varchar("config_value", 200)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}