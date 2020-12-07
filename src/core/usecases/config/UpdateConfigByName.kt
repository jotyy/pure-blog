package top.jotyy.core.usecases.config

import top.jotyy.core.data.dao.ConfigDao
import top.jotyy.core.data.param.ConfigParam

class UpdateConfigByName(
    private val configDao: ConfigDao
) {

    operator fun invoke(param: ConfigParam) =
        configDao.updateConfig(param)
}
