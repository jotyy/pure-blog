package top.jotyy.core.abstraction

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*

/**
 * Result class that wraps [Success] or [Failure]
 *
 * @author Jotyy
 */
sealed class Result<out T>

class Success<out T>(val successData: T) : Result<T>()
class Failure(val errorData: String) : Result<Nothing>()

inline fun <T> Result<T>.handle(
    successBlock: (T) -> Unit,
    failureBlock: (String) -> Unit
) {
    when (this) {
        is Success -> successBlock(successData)
        is Failure -> failureBlock(errorData)
    }
}

inline fun <T> Result<T>.onSuccess(successBlock: (T) -> Unit): Result<T> {
    if (this is Success)
        successBlock(successData)

    return this
}

inline fun <T> Result<T>.onError(errorBlock: (String) -> Unit): Result<T> {
    if (this is Failure)
        errorBlock(errorData)

    return this
}

suspend fun <T> PipelineContext<Unit, ApplicationCall>.handleResult(
    result: Result<T>,
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
