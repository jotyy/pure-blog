package top.jotyy.data.model.request

data class AddUserRequest(
    val username: String,
    val nickname: String?,
    val password: String
)

data class UpdateUserRequest(
    val username: String?,
    val password: String?,
    val nickname: String?
)

data class LoginRequest(
    val username: String,
    val password: String
)
