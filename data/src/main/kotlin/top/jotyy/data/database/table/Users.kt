package top.jotyy.data.database.table

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import top.jotyy.core.constants.LENGTH_50

/**
 * User Table
 */
object Users : IntIdTable(name = "tb_admin_user") {
    val userName = varchar("login_user_name", LENGTH_50)
    val password = varchar("login_password", LENGTH_50)
    val nickName = varchar("nick_name", LENGTH_50)
    val locked = integer("locked").default(0)
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(Users)

    var userName by Users.userName
    var nickName by Users.nickName
    var password by Users.password
    var locked by Users.locked
}
