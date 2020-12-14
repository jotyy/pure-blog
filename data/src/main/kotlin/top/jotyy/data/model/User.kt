package top.jotyy.data.model

import com.fasterxml.jackson.annotation.JsonIgnore

data class User(
    val userId: Int,
    val userName: String,
    val nickName: String,
    val password: String,
    @JsonIgnore val locked: Int
) {
    val isActive = locked == 0 // 判断账户是否可用
}
