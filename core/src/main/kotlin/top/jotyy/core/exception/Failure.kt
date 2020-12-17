package top.jotyy.core.exception

sealed class Failure(val message: String)

class ValidationFailure(message: String) : Failure(message)
class NotFoundFailure(message: String? = "Not found") : Failure(message!!)
class AlreadUsedFailure(message: String? = "Already used") : Failure(message!!)
class UnauthorizedFailure(message: String? = "Unauthorized request") : Failure(message!!)
