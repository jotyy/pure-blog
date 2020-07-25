package top.jotyy.core.data.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import top.jotyy.core.abstraction.Failure
import top.jotyy.core.abstraction.IRepository
import top.jotyy.core.abstraction.Result
import top.jotyy.core.abstraction.Success
import top.jotyy.core.data.entity.ConfigEntity
import top.jotyy.core.data.param.ConfigParam
import top.jotyy.core.data.table.Configs
import top.jotyy.core.exception.GenericError

class ConfigRepository : IRepository<ConfigEntity, ConfigParam> {

    override suspend fun getAll(): Result<List<ConfigEntity>> = dbExecute({
        Configs.selectAll().map { toEntity(it) }.toList()
    }) { emptyOrNot(it) }

    override suspend fun getById(id: Int): Result<ConfigEntity> = dbExecute({
        Configs.select { Configs.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }

    override suspend fun add(param: ConfigParam): Result<ConfigEntity> =
        dbExecute({
            Configs.insert {
                it[name] = param.configName
                it[value] = param.configValue
            }
            Configs.select { Configs.name eq param.configName }
                .mapNotNull { toEntity(it) }
                .singleOrNull()
        }) { emptyOrNot(it) }

    override suspend fun update(id: Int, param: ConfigParam): Result<ConfigEntity> = dbExecute({
        Configs.update({ Configs.id eq id }) {
            it[name] = param.configName
            it[value] = param.configValue
        }
        Configs.select { Configs.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }


    override suspend fun delete(id: Int): Result<String> = dbExecute({
        Configs.deleteWhere { Configs.id eq id }
    }) {
        if (it == 0) {
            return@dbExecute Failure(GenericError())
        } else {
            return@dbExecute Success("Deleted success")
        }
    }

    private fun toEntity(row: ResultRow): ConfigEntity = ConfigEntity(
        configName = row[Configs.name],
        configValue = row[Configs.value]
    )
}
