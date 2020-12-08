package top.jotyy.extensions

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundFailure
import top.jotyy.core.exception.UnauthorizedFailure
import top.jotyy.core.exception.ValidationFailure

/**
 * Simplify success response function
 * @param success any success data
 */
suspend fun PipelineContext<Unit, ApplicationCall>.respondSuccess(success: Any) =
    call.respond(HttpStatusCode.OK, success)

/**
 * Simplify failure response function
 * @param failure Failure object
 */
suspend fun PipelineContext<Unit, ApplicationCall>.respondFailure(failure: Failure) = when (failure) {
    is ValidationFailure -> call.respond(HttpStatusCode.BadRequest, failure.message)
    is NotFoundFailure -> call.respond(HttpStatusCode.NotFound, failure.message)
    is UnauthorizedFailure -> call.respond(HttpStatusCode.Unauthorized, failure.message)
}
