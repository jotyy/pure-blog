package top.jotyy.core.exception

sealed class Reason(val msg: String) : Throwable()

class GenericError : Reason("Something went error.")
class AuthenticateError: Reason("Authenticate failed")
class EmptyResultError: Reason("Nothing founded.")


