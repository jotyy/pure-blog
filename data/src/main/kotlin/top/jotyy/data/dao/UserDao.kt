package top.jotyy.data.dao

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.UserEntity
import top.jotyy.data.database.table.Users

/**
 * User DAO layer
 */
class UserDao {

    /**
     * Get user by name and password
     *
     * @param userName user name
     * @param password password
     * @return user
     */
    fun getUserByNameAndPassword(userName: String, password: String) = transaction {
        UserEntity.find {
            (Users.userName eq userName) and (Users.password eq password)
        }.firstOrNull()
    }

    /**
     * Add a new user
     *
     * @param userName user name
     * @param nickName nickname
     * @param password password
     * @return user added
     */
    fun addUser(userName: String, nickName: String? = null, password: String) = transaction {
        UserEntity.new {
            this.userName = userName
            this.nickName = nickName ?: userName
            this.password = password
        }
    }

    /**
     * Update user
     *
     * @param userId user id
     * @param userName user name
     * @param nickName nick name
     * @param password password
     * @return user updated
     */
    fun updateUserById(userId: Int, userName: String? = null, nickName: String? = null, password: String? = null) =
        transaction {
            UserEntity.findById(userId)
                ?.apply {
                    this.userName = userName ?: this.userName
                    this.nickName = nickName ?: this.nickName
                    this.password = password ?: this.password
                }
        }

    /**
     * If user is exist
     *
     * @param userId id
     */
    fun isUserExist(userId: Int) = transaction {
        UserEntity.findById(userId) != null
    }
}
