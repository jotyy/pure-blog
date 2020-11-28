package top.jotyy.features.user

import core.constants.USER_PATH
import core.data.repository.UserRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import top.jotyy.core.abstraction.Failure
import top.jotyy.core.abstraction.Success
import top.jotyy.core.data.param.AddUserParam

/**
 * User Router
 */
fun Routing.userRouter() {

    val userRepository = UserRepository()

    get(USER_PATH) {
    }

    get(USER_PATH) {
        withContext(Dispatchers.IO) {
            val userName = call.parameters["userName"].toString()
            userRepository.getUserByUsername(userName)
                .collect { result ->
                    when (result) {
                        is Success -> call.respond(HttpStatusCode.OK, result.successData)
                        is Failure -> call.respond(HttpStatusCode.BadRequest, result.errorData)
                    }
                }
        }
    }

    post(USER_PATH) {
        withContext(Dispatchers.IO) {
            val parameters = call.receive<AddUserParam>()
            userRepository.addUser(parameters)
                .collect { result ->
                    when (result) {
                        is Success -> {
                            call.respond(HttpStatusCode.Created, result.successData)
                        }
                        is Failure -> {
                            call.respond(HttpStatusCode.BadRequest, result.errorData)
                        }
                    }
                }
        }
    }

    put(USER_PATH) {
    }

    delete(USER_PATH) {
        withContext(Dispatchers.IO) {
            val id = call.parameters["id"]?.toInt()
            id?.let {
                userRepository.deleteUser(id)
                    .collect { result ->
                        when (result) {
                            is Success -> call.respond(HttpStatusCode.OK, "删除成功")
                            is Failure -> call.respond(HttpStatusCode.BadRequest, result.errorData)
                        }
                    }
            }
        }
    }
}
