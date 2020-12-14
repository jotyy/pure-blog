package top.jotyy.data.model.request

data class PostBlogRequest(
    val title: String,
    val subUrl: String,
    val coverImage: String?,
    val content: String,
    val categoryName: String?,
    val tags: List<String>?,
    val status: Int?,
    val enableComment: Int?
)
