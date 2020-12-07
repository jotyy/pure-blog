package core.functional

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*

/**
 * Result class that wraps [Success] or [Failure]
 *
 * @author Jotyy
 */
sealed class Either<out T>

class Success<out T>(val successData: T) : Either<T>()
class Failure(val errorData: String) : Either<Nothing>()

inline fun <T> Either<T>.handle(
    successBlock: (T) -> Unit,
    failureBlock: (String) -> Unit
) {
    when (this) {
        is Success -> successBlock(successData)
        is Failure -> failureBlock(errorData)
    }
}

inline fun <T> Either<T>.onSuccess(successBlock: (T) -> Unit): Either<T> {
    if (this is Success)
        successBlock(successData)

    return this
}

inline fun <T> Either<T>.onError(errorBlock: (String) -> Unit): Either<T> {
    if (this is Failure)
        errorBlock(errorData)

    return this
}

suspend fun <T> PipelineContext<Unit, ApplicationCall>.handleResult(
    result: Either<T>,
    successCode: HttpStatusCode = HttpStatusCode.OK,
    errorCode: HttpStatusCode = HttpStatusCode.ExpectationFailed
) {
    result.handle(
        successBlock = {
            call.respond(successCode, it!!)
        },
        failureBlock = {
            call.respond(errorCode, it)
        })
}
