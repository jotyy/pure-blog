package top.jotyy.data.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import top.jotyy.core.constants.LENGTH_200
import top.jotyy.core.constants.LENGTH_50

/**
 * Blog Table
 *
 * @author Jotyy
 */
object Blogs : Table("tb_blog") {
    val id: Column<Int> = integer("blog_id").autoIncrement()
    val title: Column<String> = varchar("blog_title", LENGTH_200)
    val subUrl: Column<String> = varchar("blog_sub_url", LENGTH_200)
    val coverImage: Column<String> = varchar("blog_cover_image", LENGTH_200)
    val content: Column<String> = text("blog_content")
    val categoryId: Column<Int> = integer("blog_category_id").default(0)
    val categoryName: Column<String> = varchar("blog_category_name", LENGTH_50)
    val tags: Column<String> = varchar("blog_tags", LENGTH_200)
    val status: Column<Int> = integer("blog_status").default(1)
    val views: Column<Int> = integer("blog_views").default(0)
    val enableComment: Column<Int> = integer("enable_comment").default(0)
    val isDeleted: Column<Int> = integer("is_deleted").default(0)
    val createdAt: Column<DateTime> = datetime("created_at").default(DateTime.now())
    val updatedAt = datetime("updated_at").default(DateTime.now())
    override val primaryKey = PrimaryKey(id, name = "blog_id")
}
