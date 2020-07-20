package top.jotyy.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object Links : Table("tb_link") {
    val id = integer("link_id")
    val type = integer("link_type")
    val name = varchar("link_name", 50)
    val url = varchar("link_url", 100)
    val description = varchar("link_description", 100)
    val rank = integer("link_rank")
    val isDeleted = integer("is_deleted")
    val createdAt = datetime("created_at")
}

