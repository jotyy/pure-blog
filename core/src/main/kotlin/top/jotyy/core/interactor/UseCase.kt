package top.jotyy.core.interactor

import top.jotyy.core.exception.Failure
import top.jotyy.core.functional.Either

abstract class UseCase<out Response, in Request> where Response : Any {

    abstract fun run(request: Request): Either<Failure, Response>

    operator fun invoke(request: Request) = run(request)
}

class None
