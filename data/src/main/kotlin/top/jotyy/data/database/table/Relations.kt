package top.jotyy.data.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

/**
 * Relation Table
 */
object Relations : IntIdTable(name = "tb_blog_tag_relation", columnName = "relation_id") {
    val blogId = reference("blog_id", Blogs.id)
    val tagId = reference("tag_id", Tags.id)
    val createdAt = datetime("created_at").default(DateTime())
}
