package top.jotyy.core.exception

sealed class Reason(val msg: String) : Throwable()

class GenericError : Reason("Something went error.")
class AuthenticateError : Reason("Authentication failed")
class ParametersError(parameterName: String) : Reason("Required $parameterName missing")
class CreationError(name: String) : Reason("Create $name failed")
class EmptyResultError : Reason("Nothing founded.")
