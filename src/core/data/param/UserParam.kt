package top.jotyy.core.data.param

import org.valiktor.functions.isNotEmpty
import org.valiktor.functions.isPositive
import org.valiktor.validate

/**
 * Add user parameters
 *
 * @param userName Account username
 * @param password Account password
 * @param nickName Account nickname
 */
data class AddUserParam(
    val userName: String,
    val password: String,
    val nickName: String
) {
    init {
        validate(this) {
            validate(AddUserParam::userName).isNotEmpty()
            validate(AddUserParam::nickName).isNotEmpty()
            validate(AddUserParam::password).isNotEmpty()
        }
    }
}

/**
 * Update user parameters
 *
 * @param id User id
 * @param userName Account username
 * @param password Account password
 * @param nickName Account nickname
 */
data class UpdateUserParams(
    val id: Int,
    val userName: String?,
    val password: String?,
    val nickName: String?
) {
    init {
        validate(this) {
            validate(UpdateUserParams::id).isPositive()
        }
    }
}
