package top.jotyy.core.service

import top.jotyy.core.abstraction.IService
import top.jotyy.core.data.entity.ConfigEntity
import top.jotyy.core.data.param.ConfigParam
import top.jotyy.core.data.repository.ConfigRepository

interface ConfigService : IService<ConfigEntity, ConfigParam>

class ConfigServiceImpl(private val repository: ConfigRepository) : ConfigService {
    override suspend fun getAll() = repository.getAll()

    override suspend fun getById(id: Int) = repository.getById(id)

    override suspend fun add(param: ConfigParam) = repository.add(param)

    override suspend fun update(id: Int, param: ConfigParam) = repository.update(id, param)

    override suspend fun delete(id: Int) = repository.delete(id)
}
