package top.jotyy.data.dao

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import top.jotyy.data.database.table.Configs
import top.jotyy.data.model.Config
import top.jotyy.data.model.request.ConfigRequest

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

    fun addConfig(param: ConfigRequest) {
        transaction {
            Configs.insert {
                it[name] = param.name
                it[value] = param.value
            }
        }
    }

    fun updateConfig(param: ConfigRequest) =
        transaction {
            Configs.update({ Configs.name eq param.name }) {
                it[value] = param.value
                it[updatedAt] = DateTime()
            }
        }

    fun isExist(name: String) =
        transaction {
            Configs.select {
                Configs.name eq name
            }.count() > 0
        }
}
