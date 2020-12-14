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
import top.jotyy.domain.auth.AppJWT
import top.jotyy.domain.extensions.toFailure

/**
 * UseCase for login authentication
 */
class Authenticate(
    private val userDao: UserDao
) : UseCase<AuthResponse, AuthRequest>() {

    override fun run(request: AuthRequest): Either<Failure, AuthResponse> = try {
        validate(request) {
            validate(AuthRequest::userName).hasSize(min = 2, max = 20)
            validate(AuthRequest::password).hasSize(min = 6, max = 20)
        }
        userDao.getUserByNameAndPassword(
            request.userName,
            request.password
        ) ?: throw UnauthorizedException()
        Either.Right(AuthResponse(AppJWT.generateToken(request.userName, request.password)))
    } catch (e: ConstraintViolationException) {
        Either.Left(e.toFailure())
    }
}
