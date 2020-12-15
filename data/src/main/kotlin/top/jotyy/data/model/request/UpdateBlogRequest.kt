package top.jotyy.data.model.request

/**
 * Update blog request model
 */
data class UpdateBlogRequest(
    val id: Int,
    val title: String?,
    val subUrl: String?,
    val coverImage: String?,
    val content: String?,
    val categoryId: Int?,
    val categoryName: String?,
    val tags: List<String>?,
    val status: Int?,
    val enableComment: Int?
)
