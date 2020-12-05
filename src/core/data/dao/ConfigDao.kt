package top.jotyy.core.data.dao

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import top.jotyy.core.data.model.Config
import top.jotyy.core.data.param.ConfigParam
import top.jotyy.core.data.table.Configs

class ConfigDao {
    fun getAllConfigs() =
        transaction {
            Configs.selectAll()
                .map {
                    Config(
                        it[Configs.name],
                        it[Configs.value]
                    )
                }
        }

    fun addConfig(param: ConfigParam) {
        transaction {
            Configs.insert {
                it[name] = param.configName
                it[value] = param.configValue
            }
        }
    }

    fun updateConfig(param: ConfigParam) {
        transaction {
            Configs.update({ Configs.name eq param.configName }) {
                it[value] = param.configValue
                it[updatedAt] = DateTime()
            }
        }
    }
}
