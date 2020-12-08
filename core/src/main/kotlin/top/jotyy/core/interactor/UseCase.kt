package top.jotyy.core.interactor

import top.jotyy.core.exception.Failure
import top.jotyy.core.functional.Either

abstract class UseCase<out Type, in Request> where Type : Any {

    abstract fun run(request: Request): Either<Failure, Type>

    operator fun invoke(request: Request) = run(request)
}

class None
