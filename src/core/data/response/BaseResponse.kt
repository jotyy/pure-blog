package top.jotyy.core.data.response

data class BaseResponse<T>(
    val code: Int,
    val msg: String,
    val data: T?
)
