package top.jotyy.core.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import top.jotyy.bootstrap.LENGTH_100
import top.jotyy.bootstrap.LENGTH_200

/**
 * Config Table
 *
 * @author Jotyy
 */
object Configs : Table("tb_config") {
    val name = varchar("config_name", LENGTH_100)
    val value = varchar("config_value", LENGTH_200)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
