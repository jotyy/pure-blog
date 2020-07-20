package router

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.*
import top.jotyy.model.dto.UserDTO
import top.jotyy.service.UserService


private val userService by lazy { UserService() }

fun Routing.userRouter() {
    get("/users") {
        call.respond(mapOf("users" to userService.getAll()))
    }

    post("/users") {
        val userDTO = call.receive<UserDTO>()
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
                userService.insert(userDTO)
                call.respond(HttpStatusCode.Created)
            }
        }
    }

    put("/users") {
        val params = call.receiveParameters()
        val userDTO = UserDTO(
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

    delete("/users") {
        val id = call.parameters["id"]
        if (id != null) {
            userService.delete(id.toInt())
            call.respond(mapOf("msg" to "ID can not be null"))
        } else {
            call.respond(HttpStatusCode.OK)
        }
    }
}
