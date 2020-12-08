package top.jotyy.router

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.runBlocking
import org.koin.ktor.ext.inject
import top.jotyy.core.constants.FailureMessages
import top.jotyy.core.constants.Router
import top.jotyy.core.exception.ConfigParamsException
import top.jotyy.core.functional.onFailure
import top.jotyy.core.functional.onSuccess
import top.jotyy.data.params.ConfigParams
import top.jotyy.domain.usecases.UpdateConfigByName

fun Routing.configRouter() {
    val updateConfigByName by inject<UpdateConfigByName>()

    put(Router.CONFIG_PATH) {
        val params = runCatching {
            call.receive<ConfigParams>()
        }.getOrElse {
            throw BadRequestException(FailureMessages.CONFIG_PARAMS_ERROR)
        }

        updateConfigByName(params)
            .onSuccess {
                runBlocking {
                    call.respond(HttpStatusCode.OK, "Success")
                }
            }
            .onFailure {
                runBlocking {
                    call.respond(HttpStatusCode.BadRequest, (it as ConfigParamsException).msg)
                }
            }
    }
}
