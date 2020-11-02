package top.jotyy.features.tag

import core.data.constants.TAG_PATH
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import top.jotyy.core.abstraction.handleResult
import top.jotyy.core.data.param.TagParam
import top.jotyy.core.service.TagService

fun Routing.tagRoute() {
    val service by inject<TagService>()

    get(TAG_PATH) {
        handleResult(service.getAll())
    }

    get("$TAG_PATH/{id}") {
        call.parameters["id"]?.let { id ->
            handleResult(service.getById(id.toInt()))
        } ?: call.respond(HttpStatusCode.BadRequest, "Parameter id must not be null")
    }

    post(TAG_PATH) {
        call.parameters["tagName"]?.let { tagName ->
            handleResult(service.add(TagParam(tagName)))
        } ?: call.respond(HttpStatusCode.BadRequest, "Parameter tagName must not be null")
    }

    put(TAG_PATH) {
        val tagName = call.parameters["tagName"]
        val id = call.parameters["id"]
        if (id.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Parameter id must not be null")
            return@put
        }
        if (tagName.isNullOrEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Parameter tagName must not be null")
            return@put
        }

        handleResult(
            service.update(id.toInt(), TagParam(tagName))
        )
    }

    delete(TAG_PATH) {
        call.parameters["id"]?.let { id ->
            handleResult(service.delete(id.toInt()))
        } ?: call.respond(HttpStatusCode.BadRequest, "Parameter id must not be null")
    }
}
