package top.jotyy.core.data.dao

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.core.data.model.Config
import top.jotyy.core.data.param.ConfigParam
import top.jotyy.core.data.table.ConfigEntity
import top.jotyy.core.data.table.Configs

class ConfigDao {
    fun getAllConfigs() =
        transaction {
            ConfigEntity.all()
        }.let {
            it.map { entity ->
                Config.fromEntity(entity)
            }
        }

    fun addConfig(param: ConfigParam) {
        transaction {
            ConfigEntity.new {
                name = param.configName
                value = param.configValue
            }
        }
    }

    fun updateConfig(param: ConfigParam) {
        transaction {
            ConfigEntity.find { Configs.name eq param.configName }
                .first()
                .value = param.configValue
        }
    }
}
