package top.jotyy.domain.usecases

import top.jotyy.core.exception.ConfigParamsException
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.params.ConfigParams

/**
 * Initialize site configuration
 */
class InitializeSite(
    private val configDao: ConfigDao
) : UseCase<None, List<ConfigParams>>() {

    override fun run(params: List<ConfigParams>) = try {
        params.forEach { param ->
            configDao.updateConfig(param)
        }
        Either.Right(None())
    } catch (e: Exception) {
        Either.Left(ConfigParamsException())
    }
}
