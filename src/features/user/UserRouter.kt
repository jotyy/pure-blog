package top.jotyy.features.user

import core.data.constants.USER_PATH
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import top.jotyy.core.data.param.UserParam
import top.jotyy.core.service.UserService

/**
 * User Router
 */
fun Routing.userRouter() {
    val userService by inject<UserService>()

    get(USER_PATH) {
        call.respond(mapOf("users" to userService.getAll()))
    }

    get("$USER_PATH/{id}") {
        val id = call.parameters["id"]
        call.respond(userService.getById(id?.toInt() ?: 0))
    }

    post(USER_PATH) {
        val userDTO = call.receive<UserParam>()
        when {
            userDTO.userName.isNullOrEmpty() -> {
                call.respond(mapOf("msg" to "Username can not be null"))
            }
            userDTO.nickName.isNullOrEmpty() -> {
                call.respond(mapOf("msg" to "Nickname can not be null"))
            }
            userDTO.password.isNullOrEmpty() -> {
                call.respond(mapOf("msg" to "Password can not be null"))
            }
            else -> {
                userService.add(userDTO)
                call.respond(HttpStatusCode.Created)
            }
        }
    }

    put(USER_PATH) {
        val params = call.receiveParameters()
        val userDTO = UserParam(
            userName = params["userName"],
            nickName = params["nickName"],
            password = params["password"]
        )
        val id = params["id"]
        if (id != null) {
            userService.update(userDTO, id.toInt())
            call.respond(HttpStatusCode.Accepted)
        } else {
            call.respond(mapOf("msg" to "ID can not be null"))
        }
    }

    delete(USER_PATH) {
        val id = call.parameters["id"]
        if (id != null) {
            userService.delete(id.toInt())
            call.respond(mapOf("msg" to "ID can not be null"))
        } else {
            call.respond(HttpStatusCode.OK)
        }
    }
}
