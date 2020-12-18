package top.jotyy.core.exception

class NotFoundException(item: String?) : Exception("$item not found")
class AlreadyUsedException(item: String?) : Exception("$item already used")
class UnauthorizedException : Exception("Unauthorized")

fun NotFoundException.toFailure() = NotFoundFailure(message = this.message)
fun AlreadyUsedException.toFailure() = AlreadUsedFailure(message = this.message)
fun UnauthorizedException.toFailure() = UnauthorizedFailure(message = this.message)
