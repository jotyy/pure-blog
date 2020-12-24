package top.jotyy.data.model.request

data class LinkRequest(
    val type: Int,
    val name: String,
    val url: String,
    val description: String
)
