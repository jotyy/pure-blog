package top.jotyy.extensions

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import top.jotyy.core.exception.*

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
    is AlreadUsedFailure -> call.respond(HttpStatusCode.Forbidden, failure.message)
}
