package top.jotyy.domain.usecases

import top.jotyy.core.exception.ConfigParamsException
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.params.ConfigParams

class UpdateConfigByName(
    private val configDao: ConfigDao
) : UseCase<None, ConfigParams>() {

    override fun run(params: ConfigParams) = try {
        configDao.updateConfig(params)
        Either.Right(None())
    } catch (e: Exception) {
        e.printStackTrace()
        Either.Left(ConfigParamsException())
    }
}
