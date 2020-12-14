package top.jotyy.data.database.table

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import top.jotyy.core.constants.LENGTH_200

/**
 * Blog Table
 */
object Blogs : IntIdTable(name = "tb_blog", columnName = "blog_id") {
    val title: Column<String> = varchar("blog_title", LENGTH_200)
    val subUrl: Column<String> = varchar("blog_sub_url", LENGTH_200)
    val coverImage = varchar("blog_cover_image", LENGTH_200).nullable()
    val content: Column<String> = text("blog_content")
    val categoryId = reference("blog_category_id", Categories.id).nullable()
    val categoryName = reference("blog_category_name", Categories.name).nullable()
    val tags = varchar("blog_tags", LENGTH_200).nullable()
    val status: Column<Int> = integer("blog_status").default(1)
    val views: Column<Int> = integer("blog_views").default(0)
    val enableComment: Column<Int> = integer("enable_comment").default(0)
    val isDeleted: Column<Int> = integer("is_deleted").default(0)
    val createdAt: Column<DateTime> = datetime("created_at").default(DateTime.now())
    val updatedAt = datetime("updated_at").default(DateTime.now())
}

/**
 * Blog Entity
 */
class BlogEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BlogEntity>(Blogs)

    var title by Blogs.title
    var subUrl by Blogs.subUrl
    var coverImage by Blogs.coverImage
    var content by Blogs.content
    var categoryId by Blogs.categoryId
    var categoryName by Blogs.categoryName
    var tags by Blogs.tags
    var status by Blogs.status
    var views by Blogs.views
    var enableComment by Blogs.enableComment
    var isDeleted by Blogs.isDeleted
    var createdAt by Blogs.createdAt
    var updatedAt by Blogs.updatedAt
}
