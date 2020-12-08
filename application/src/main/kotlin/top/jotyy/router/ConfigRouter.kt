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
import top.jotyy.core.exception.Failure
import top.jotyy.core.functional.onFailure
import top.jotyy.core.functional.onSuccess
import top.jotyy.data.model.request.ConfigRequest
import top.jotyy.domain.usecases.UpdateConfigByName

fun Routing.configRouter() {
    val updateConfigByName by inject<UpdateConfigByName>()

    put(Router.CONFIG_PATH) {
        val configRequest = runCatching {
            call.receive<ConfigRequest>()
        }.getOrElse {
            throw BadRequestException(FailureMessages.CONFIG_PARAMS_ERROR)
        }

        updateConfigByName(configRequest)
            .onSuccess {
                runBlocking {
                    call.respond(HttpStatusCode.OK, "Success")
                }
            }
            .onFailure {
                runBlocking {
                    call.respond(HttpStatusCode.BadRequest, (it as Failure.FeatureFailure).message)
                }
            }
    }
}
