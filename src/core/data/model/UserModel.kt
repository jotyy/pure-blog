package top.jotyy.core.data.model

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * User Entity
 *
 * @author Jotyy
 */
data class UserModel(
    val userId: Int,
    val userName: String,
    val nickName: String,
    val password: String,
    @JsonIgnore val locked: Int
) {
    val isActive = locked == 0 // 判断账户是否可用
}
