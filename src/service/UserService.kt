package top.jotyy.service

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.model.User
import top.jotyy.model.dto.UserDTO
import top.jotyy.model.entity.UserEntity

class UserService {

    // 查询所有用户
    fun getAll(): List<UserEntity> = transaction {
        User.all().map { user ->
            UserEntity(
                userId = user.id.value,
                userName = user.userName,
                nickName = user.nickName,
                password = user.password,
                locked = user.locked
            )
        }
    }

    // 根据id查询用户
    fun getById(id: Int): UserEntity =
        transaction {
            val user = User.findById(id)
            UserEntity(
                userId = user!!.id.value,
                userName = user.userName,
                nickName = user.nickName,
                password = user.password,
                locked = user.locked
            )
        }

    // 新增用户
    fun insert(userDTO: UserDTO) {
        transaction {
            User.new {
                userName = userDTO.userName!!
                nickName = userDTO.nickName!!
                password = userDTO.password!!
            }
        }
    }

    // 修改用户
    fun update(userDTO: UserDTO, id: Int) {
        transaction {
            User.findById(id)?.apply {
                userName = userDTO.userName ?: userName
                nickName = userDTO.nickName ?: nickName
                password = userDTO.password ?: password
            }
        }
    }

    // 删除用户
    fun delete(id: Int) {
        transaction {
            User.findById(id)?.delete()
        }
    }
}
