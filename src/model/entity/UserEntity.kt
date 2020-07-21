package top.jotyy.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * User Entity
 *
 * @author Jotyy
 */
data class UserEntity(
    val userId: Int,
    val userName: String,
    val nickName: String,
    val password: String,
    @JsonIgnore val locked: Int
) {
    val isActive = locked == 0  // 判断账户是否可用
}
