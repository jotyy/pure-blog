package top.jotyy.domain.usecases

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.validate
import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.UnauthorizedException
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.UserDao
import top.jotyy.data.model.request.AuthRequest
import top.jotyy.data.model.response.AuthResponse
import top.jotyy.domain.extensions.toFailure

class Authenticate(
    private val userDao: UserDao
) : UseCase<AuthResponse, AuthRequest>() {

    override fun run(request: AuthRequest): Either<Failure, AuthResponse> = try {
        validate(request) {
            validate(AuthRequest::username).hasSize(min = 2, max = 20)
            validate(AuthRequest::password).hasSize(min = 6, max = 20)
        }
        val user = userDao.getUserByNameAndPassword(
            request.username,
            request.password
        ) ?: throw UnauthorizedException()
        Either.Right(AuthResponse(""))
    } catch (e: ConstraintViolationException) {
        Either.Left(e.toFailure())
    }
}
