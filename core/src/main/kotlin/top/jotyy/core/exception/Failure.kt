package top.jotyy.core.exception

import top.jotyy.core.constants.FailureMessages

sealed class Failure {
    object GenericFailure : Failure()

    abstract class FeatureFailure(val msg: String) : Failure()
}

class ConfigParamsException : Failure.FeatureFailure(FailureMessages.CONFIG_PARAMS_ERROR)
