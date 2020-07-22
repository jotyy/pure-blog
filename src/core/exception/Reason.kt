package core.exception

sealed class Reason(val msg: String) : Throwable()

class GenericError : Reason("Something went error.")
class EmptyResultError: Reason("There is nothing.")


