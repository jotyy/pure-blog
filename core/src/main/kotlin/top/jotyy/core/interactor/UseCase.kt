package top.jotyy.core.interactor

import top.jotyy.core.exception.Failure
import top.jotyy.core.functional.Either

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params) = run(params)
}
