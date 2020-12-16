package top.jotyy.router

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import top.jotyy.core.constants.Router
import top.jotyy.core.functional.onAsyncFailure
import top.jotyy.core.functional.onAsyncSuccess
import top.jotyy.data.model.request.AuthRequest
import top.jotyy.data.model.request.InitializeSiteRequest
import top.jotyy.domain.domain.auth.AuthUseCase
import top.jotyy.domain.usecases.config.InitializeSiteUseCase
import top.jotyy.extensions.respondFailure
import top.jotyy.extensions.respondSuccess

/**
 * Application global interfaces
 */
fun Routing.appRouter() {
    val initializeSite by inject<InitializeSiteUseCase>()
    val authenticate by inject<AuthUseCase>()

    post(Router.INIT_PATH) {
        val request = runCatching {
            call.receive<InitializeSiteRequest>()
        }.getOrElse {
            throw BadRequestException("Request parameters error")
        }

        initializeSite(request)
            .onAsyncSuccess {
                respondSuccess(it)
            }
            .onAsyncFailure {
                respondFailure(it)
            }
    }

    post(Router.LOGIN_PATH) {
        val request = runCatching { call.receive<AuthRequest>() }
            .getOrElse { throw BadRequestException("Request parameters error") }

        authenticate(request)
            .onAsyncSuccess {
                respondSuccess(it)
            }
            .onAsyncFailure {
                respondFailure(it)
            }
    }
}
