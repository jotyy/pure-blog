package top.jotyy.core.exception

sealed class Failure(val message: String)

class ValidationFailure(message: String) : Failure(message)
class NotFoundFailure(message: String? = "Not found") : Failure(message!!)
class UnauthorizedFailure(message: String? = "Unauthorized request") : Failure(message!!)
