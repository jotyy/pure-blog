package top.jotyy.data.dao

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import top.jotyy.data.database.table.Configs
import top.jotyy.data.model.Config

/**
 * Config DAO layer
 */
class ConfigDao {

    /**
     * Get all configs
     */
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

    /**
     * Add config
     *
     * @param name config name
     * @param value config value
     */
    fun addConfig(name: String, value: String) {
        transaction {
            Configs.insert {
                it[this.name] = name
                it[this.value] = value
            }
        }
    }

    /**
     * Update config
     *
     * @param name config name
     * @param value config value
     */
    fun updateConfig(name: String, value: String) =
        transaction {
            Configs.update({ Configs.name eq name }) {
                it[this.value] = value
                it[updatedAt] = DateTime()
            }
        }

    /**
     * If config exist
     *
     * @param name config name
     */
    fun isExist(name: String) =
        transaction {
            Configs.select {
                Configs.name eq name
            }.count() > 0
        }
}
