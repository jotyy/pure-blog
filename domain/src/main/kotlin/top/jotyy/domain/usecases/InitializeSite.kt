package top.jotyy.domain.usecases

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.model.request.ConfigRequest
import top.jotyy.domain.extensions.toFailure

/**
 * Initialize site configuration
 */
class InitializeSite(
    private val configDao: ConfigDao
) : UseCase<None, List<ConfigRequest>>() {

    override fun run(params: List<ConfigRequest>) = try {
        params.forEach { param ->
            validate(param) {
                validate(ConfigRequest::name).isNotEmpty()
                validate(ConfigRequest::value).isNotEmpty()
            }

            checkConfigExistOrThrowException(param.name)

            configDao.updateConfig(param)
        }
        Either.Right(None())
    } catch (e: ConstraintViolationException) {
        Either.Left(e.toFailure())
    }

    private fun checkConfigExistOrThrowException(name: String) {
        if (!configDao.isExist(name)) {
            throw NotFoundException(name)
        }
    }
}
