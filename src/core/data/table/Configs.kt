package top.jotyy.core.data.table

import core.constants.LENGTH_100
import core.constants.LENGTH_200
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

/**
 * Config Table
 */
object Configs : Table("tb_config") {
    val name = varchar("config_name", LENGTH_100).uniqueIndex()
    val value = varchar("config_value", LENGTH_200)
    val createdAt = datetime("created_at").default(DateTime())
    val updatedAt = datetime("updated_at").default(DateTime())
}
