package top.jotyy.core.data.param

data class CommentParam(
    val blogId: Int,
    val commentator: String,
    val email: String,
    val webSiteUrl: String,
    val commentBy: String,
    val commentatorIp: String
)
