package top.jotyy.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object Relations : Table("tb_blog_tag_relation") {
    val relationId = integer("relation_id")
    val blogId = integer("blog_id")
    val tagId = integer("tag_id")
    val createdAt = datetime("created_at")
}
