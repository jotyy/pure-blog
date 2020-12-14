package top.jotyy.data.dao

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.BlogEntity

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
        categoryName: String?,
        tags: Array<String>,
        status: Int?,
        enableComment: Int?
    ) = transaction {
        BlogEntity.new {
            this.title = title
            this.subUrl = subUrl
            this.coverImage = coverImage
            this.content = content
            this.categoryName = categoryName
            this.tags = tags.toString()
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
     * @param categoryName Category of blog
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
        categoryName: String?,
        tags: Array<String>,
        status: Int?,
        enableComment: Int?
    ) = transaction {
        BlogEntity.findById(id)?.apply {
            this.title = title ?: this.title
            this.subUrl = subUrl ?: this.subUrl
            this.coverImage = coverImage ?: this.coverImage
            this.content = content ?: this.content
            this.categoryName = categoryName ?: this.categoryName
            this.tags = if (tags.isEmpty()) this.tags else tags.toString()
            this.status = status ?: this.status
            this.enableComment = enableComment ?: this.enableComment
        }
    }

    /**
     * Get blog by id
     *
     * @param id blog id
     */
    fun getBlogById(id: Int) = transaction {
        BlogEntity.findById(id)
    }
}
