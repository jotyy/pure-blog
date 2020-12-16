package top.jotyy.domain.usecases.config

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.NotFoundFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.model.request.ConfigRequest
import top.jotyy.domain.extensions.toFailure

/**
 * UseCase of update configuration
 */
class UpdateConfigUseCase(
    private val configDao: ConfigDao
) : UseCase<None, ConfigRequest>() {

    override fun run(request: ConfigRequest) = try {
        validate(request) {
            validate(ConfigRequest::name).isNotEmpty()
            validate(ConfigRequest::value).isNotEmpty()
        }
        checkConfigExistOrThrowException(request.name)

        configDao.updateConfig(request.name, request.value)
        Either.Right(None())
    } catch (e: ConstraintViolationException) {
        Either.Left(e.toFailure())
    } catch (e: NotFoundException) {
        Either.Left(NotFoundFailure(e.message))
    }

    private fun checkConfigExistOrThrowException(name: String) {
        if (!configDao.isExist(name)) {
            throw NotFoundException(name)
        }
    }
}
