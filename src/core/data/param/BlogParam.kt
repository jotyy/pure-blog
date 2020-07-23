package top.jotyy.core.data.param

data class BlogParam(
    val blogTitle: String,
    val blogSubUrl: String?,
    val blogCoverImage: String?,
    val blogContent: String,
    val blogCategoryId: Int,
    val blogCategoryName: String,
    val blogTags: String,
    val enableComment: Int
)
