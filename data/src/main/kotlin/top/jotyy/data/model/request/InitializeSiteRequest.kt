package top.jotyy.data.model.request

data class InitializeSiteRequest(
    val siteName: String,
    val siteUrl: String,
    val userName: String,
    val password: String
)
