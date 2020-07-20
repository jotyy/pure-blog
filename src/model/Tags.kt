package top.jotyy.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object Tags : Table("tb_tag") {
    val id = integer("tag_id").autoIncrement()
    val name = varchar("tag_name", 100)
    val isDeleted = integer("is_deleted").default(0)
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id, name = "tag_id")
}