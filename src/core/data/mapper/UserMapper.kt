package top.jotyy.core.data.mapper

import top.jotyy.core.data.model.UserModel
import top.jotyy.core.data.table.User

fun User.toModel() =
    UserModel(
        userId = id.value,
        userName = userName,
        nickName = nickName,
        password = "",
        locked = locked
    )
