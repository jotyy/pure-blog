package top.jotyy.features

import core.constants.CONFIG_PATH
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import top.jotyy.core.data.param.ConfigParam
import top.jotyy.core.data.repository.ConfigRepository
import top.jotyy.core.usecases.config.UpdateConfigByName

fun Routing.configRouter() {
    val configRepository: ConfigRepository by inject()
    val updateConfigByName: UpdateConfigByName by inject()

    get(CONFIG_PATH) {
        call.respond(HttpStatusCode.OK, configRepository.getConfigs())
    }

    post(CONFIG_PATH) {
        val param = call.receive<ConfigParam>()
        configRepository.addConfig(param)
        call.respond(HttpStatusCode.OK, param)
    }

    put(CONFIG_PATH) {
        val param = call.receive<ConfigParam>()
        updateConfigByName(param)
        call.respond(HttpStatusCode.OK, param)
    }
}
