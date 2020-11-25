package top.jotyy.core.data.table

import core.constants.LENGTH_50
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

/**
 * User Table
 *
 * @author Jotyy
 */
object Users : IntIdTable(name = "tb_admin_user") {
    val userName: Column<String> = varchar("login_user_name", LENGTH_50)
    val password: Column<String> = varchar("login_password", LENGTH_50)
    val nickName: Column<String> = varchar("nick_name", LENGTH_50)
    val locked: Column<Int> = integer("locked").default(0)
}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var userName by Users.userName
    var nickName by Users.nickName
    var password by Users.password
    var locked by Users.locked
}
