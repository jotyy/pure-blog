package top.jotyy.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import top.jotyy.core.constants.LENGTH_100
import top.jotyy.core.constants.LENGTH_200

/**
 * Config Table
 */
object Configs : Table("tb_config") {
    val name = varchar("config_name", LENGTH_100).uniqueIndex()
    val value = varchar("config_value", LENGTH_200)
    val createdAt = datetime("created_at").default(DateTime())
    val updatedAt = datetime("updated_at").default(DateTime())
}
