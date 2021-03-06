package top.jotyy.data.model.response

import top.jotyy.core.extensions.toFormatString
import top.jotyy.data.database.table.BlogEntity

data class BlogResponse(
    val blogId: Int,
    val blogTitle: String,
    val blogSubUrl: String,
    val blogCoverImage: String?,
    val blogContent: String,
    val blogCategoryId: Int?,
    val blogCategoryName: String?,
    val blogTags: String?,
    val blogStatus: Int,
    val blogViews: Int,
    val enableComment: Int,
    val createdAt: String,
    val updatedAt: String
) {
    companion object {
        fun fromEntity(entity: BlogEntity) = BlogResponse(
            blogId = entity.id.value,
            blogTitle = entity.title,
            blogSubUrl = entity.subUrl,
            blogCoverImage = entity.coverImage,
            blogContent = entity.content,
            blogCategoryId = entity.categoryId?.value,
            blogCategoryName = entity.categoryName,
            blogTags = entity.tags,
            blogStatus = entity.status,
            blogViews = entity.views,
            enableComment = entity.enableComment,
            createdAt = entity.createdAt.toDate().toFormatString(),
            updatedAt = entity.updatedAt.toDate().toFormatString()
        )
    }
}
