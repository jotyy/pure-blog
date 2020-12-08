package top.jotyy.data.dao

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.UserEntity
import top.jotyy.data.database.table.Users

class UserDao {

    fun getUserByNameAndPassword(userName: String, password: String) {
        UserEntity.find {
            (Users.userName eq userName) and (Users.password eq password)
        }.firstOrNull()
    }

    fun addUser(userName: String, nickName: String?, password: String) = transaction {
        UserEntity.new {
            this.userName = userName
            this.nickName = nickName ?: userName
            this.password = password
        }
    }

    fun updateUserById(userId: Int, userName: String?, nickName: String?, password: String?) = transaction {
        UserEntity.findById(userId)
            ?.apply {
                this.userName = userName ?: this.userName
                this.nickName = nickName ?: this.nickName
                this.password = password ?: this.password
            }
    }

    fun isUserExist(userId: Int) = transaction {
        UserEntity.findById(userId) != null
    }
}
