package top.jotyy.repository

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.model.User
import top.jotyy.model.dto.UserDTO
import top.jotyy.model.entity.UserEntity

class UserRepository {
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

    fun add(userDTO: UserDTO) {
        transaction {
            User.new {
                userName = userDTO.userName!!
                nickName = userDTO.nickName!!
                password = userDTO.password!!
            }
        }
    }

    fun update(userDTO: UserDTO, id: Int) {
        transaction {
            User.findById(id)?.apply {
                userName = userDTO.userName ?: userName
                nickName = userDTO.nickName ?: nickName
                password = userDTO.password ?: password
            }
        }
    }

    fun delete(id: Int) {
        transaction {
            User.findById(id)?.delete()
        }
    }
}
