package top.jotyy.data.database.table

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import top.jotyy.core.constants.LENGTH_100
import top.jotyy.core.constants.LENGTH_50

/**
 * Link Table
 */
object Links : IntIdTable(name = "tb_link", columnName = "link_id") {
    val type = integer("link_type")
    val name = varchar("link_name", LENGTH_50)
    val url = varchar("link_url", LENGTH_100)
    val description = varchar("link_description", LENGTH_100)
    val rank = integer("link_rank")
    val isDeleted = integer("is_deleted")
    val createdAt = datetime("created_at").default(DateTime())
    val updatedAt = datetime("updated_at").default(DateTime())
}

/**
 * Link entity
 */
class LinkEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LinkEntity>(Links)

    var type by Links.type
    var name by Links.name
    var url by Links.url
    var description by Links.description
    var rank by Links.rank
    var isDeleted by Links.isDeleted
    var createdAt by Links.createdAt
    var updatedAt by Links.updatedAt
}
