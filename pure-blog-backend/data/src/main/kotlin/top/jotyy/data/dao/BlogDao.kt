package top.jotyy.data.dao

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import top.jotyy.data.database.table.BlogEntity
import top.jotyy.data.database.table.Categories
import top.jotyy.data.model.response.BlogResponse

/**
 * Blog DAO layer
 */
class BlogDao {

    /**
     * Add new blog
     *
     * @param title Blog title
     * @param subUrl Blog path
     * @param coverImage Blog cover image path
     * @param content Blog content
     * @param categoryName Category of blog
     * @param tags Tags of blog
     * @param status Blog status
     * @param enableComment If blog can be commented
     */
    fun addBlog(
        title: String,
        subUrl: String,
        coverImage: String?,
        content: String,
        categoryId: Int?,
        categoryName: String?,
        tags: String?,
        status: Int?,
        enableComment: Int?
    ) = transaction {
        BlogEntity.new {
            this.title = title
            this.subUrl = subUrl
            this.coverImage = coverImage
            this.content = content
            this.categoryId = categoryId?.let { EntityID(it, Categories) }
            this.categoryName = categoryName
            this.tags = tags
            this.status = status ?: 0
            this.enableComment = enableComment ?: 0
        }
    }

    /**
     * Delete blog
     *
     * @param id blog id
     */
    fun deleteBlog(id: Int) = transaction {
        BlogEntity.findById(id)?.delete()
    }

    /**
     * Update blog
     *
     * @param id Blog id
     * @param title Blog title
     * @param subUrl Blog path
     * @param coverImage Blog cover image path
     * @param content Blog content
     * @param categoryId Category id
     * @param categoryName Category name
     * @param tags Tags of blog
     * @param status Blog status
     * @param enableComment If blog can be commented
     */
    fun updateBlog(
        id: Int,
        title: String?,
        subUrl: String?,
        coverImage: String?,
        content: String?,
        categoryId: Int?,
        categoryName: String?,
        tags: String?,
        status: Int?,
        enableComment: Int?
    ) = transaction {
        BlogEntity.findById(id)?.apply {
            this.title = title ?: this.title
            this.subUrl = subUrl ?: this.subUrl
            this.coverImage = coverImage ?: this.coverImage
            this.content = content ?: this.content
            this.categoryId = if (categoryId != null) EntityID(categoryId, Categories) else this.categoryId
            this.categoryName = categoryName ?: this.categoryName
            this.tags = tags ?: this.tags
            this.status = status ?: this.status
            this.enableComment = enableComment ?: this.enableComment
            this.updatedAt = DateTime()
        }?.let { BlogResponse.fromEntity(it) }
    }

    /**
     * Get blogs
     *
     * @param limit num of query
     * @param offset offset of first record
     */
    fun getBlogs(limit: Int = 10, offset: Long = 0L) = transaction {
        BlogEntity.all().limit(limit, offset).map {
            BlogResponse.fromEntity(it)
        }
    }

    /**
     * Get blog by id
     *
     * @param id blog id
     */
    fun getBlogById(id: Int) = transaction {
        BlogEntity.findById(id)?.let { BlogResponse.fromEntity(it) }
    }
}
