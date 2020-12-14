package top.jotyy.core.exception

class NotFoundException(item: String?) : Exception("$item not found")
class UnauthorizedException : Exception("Unauthorized")
