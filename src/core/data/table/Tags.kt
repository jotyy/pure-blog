package top.jotyy.core.data.table

import core.constants.LENGTH_100
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

/**
 * Tag Table
 *
 * @author Jotyy
 */
object Tags : Table("tb_tag") {
    val id = integer("tag_id").autoIncrement()
    val name = varchar("tag_name", LENGTH_100)
    val isDeleted = integer("is_deleted").default(0)
    val createdAt = datetime("created_at").default(DateTime.now())
    override val primaryKey = PrimaryKey(id, name = "tag_id")
}
