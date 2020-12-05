package top.jotyy.core.data.table

import core.constants.LENGTH_100
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

/**
 * Tag Table
 *
 * @author Jotyy
 */
object Tags : IntIdTable(name = "tb_tag", columnName = "tag_id") {
    val name = varchar("tag_name", LENGTH_100)
    val isDeleted = integer("is_deleted")
        .default(0)
    val createdAt = datetime("created_at")
        .default(DateTime.now())
}

class TagEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TagEntity>(Tags)

    var name by Tags.name
}
