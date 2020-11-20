package core.data.repository

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.core.data.entity.UserEntity
import top.jotyy.core.data.param.UserParam
import top.jotyy.core.data.table.User

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

    fun add(userParam: UserParam) {
        transaction {
            User.new {
                userName = userParam.userName!!
                nickName = userParam.nickName!!
                password = userParam.password!!
            }
        }
    }

    fun update(userParam: UserParam, id: Int) {
        transaction {
            User.findById(id)?.apply {
                userName = userParam.userName ?: userName
                nickName = userParam.nickName ?: nickName
                password = userParam.password ?: password
            }
        }
    }

    fun delete(id: Int) {
        transaction {
            User.findById(id)?.delete()
        }
    }
}
