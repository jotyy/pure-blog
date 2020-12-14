package top.jotyy.router

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import top.jotyy.core.constants.Router
import top.jotyy.core.functional.onAsyncFailure
import top.jotyy.core.functional.onAsyncSuccess
import top.jotyy.data.model.request.ConfigRequest
import top.jotyy.domain.usecases.UpdateConfigByName
import top.jotyy.extensions.respondFailure
import top.jotyy.extensions.respondSuccess

/**
 * Configurations router
 */
fun Routing.configRouter() {
    val updateConfigByName by inject<UpdateConfigByName>()

    put(Router.CONFIG_PATH) {
        val configRequest = runCatching {
            call.receive<ConfigRequest>()
        }.getOrElse {
            throw BadRequestException("Request parameters error")
        }

        updateConfigByName(configRequest)
            .onAsyncSuccess {
                respondSuccess(it)
            }
            .onAsyncFailure {
                respondFailure(it)
            }
    }
}
