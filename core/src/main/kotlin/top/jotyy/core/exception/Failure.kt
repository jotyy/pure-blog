package top.jotyy.core.exception

sealed class Failure {
    object GenericFailure : Failure()

    abstract class FeatureFailure(val message: String) : Failure()
}

class ValidationFailure(message: String) : Failure.FeatureFailure(message)
class NotFoundFailure(message: String? = "Not found") : Failure.FeatureFailure(message!!)
