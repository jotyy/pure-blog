package top.jotyy.core.data.repository

import top.jotyy.core.data.dao.ConfigDao
import top.jotyy.core.data.param.ConfigParam

class ConfigRepository(private val configDao: ConfigDao) {

    fun getConfigs() = configDao.getAllConfigs()

    fun addConfig(param: ConfigParam) = configDao.addConfig(param)

    fun updateConfig(param: ConfigParam) = configDao.updateConfig(param)
}
