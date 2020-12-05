package top.jotyy.core.data.table

import core.constants.LENGTH_100
import core.constants.LENGTH_200
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

/**
 * Website Config Table
 */
object Configs : IntIdTable("tb_config", columnName = "config_id") {
    val name = varchar("config_name", LENGTH_100)
    val value = varchar("config_value", LENGTH_200)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
    override val primaryKey = PrimaryKey(id, name = "config_id")
}

class ConfigEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ConfigEntity>(Configs)
    var name by Configs.name
    var value by Configs.value
    var createdAt by Configs.createdAt
    var updateAt by Configs.updatedAt
}
