package top.jotyy.core.service

import top.jotyy.core.abstraction.IService
import top.jotyy.core.data.entity.LinkEntity
import top.jotyy.core.data.param.LinkParam
import top.jotyy.core.data.repository.LinkRepository

interface LinkService : IService<LinkEntity, LinkParam>

class LinkServiceImpl(private val repository: LinkRepository) : LinkService {
    override suspend fun getAll() = repository.getAll()

    override suspend fun getById(id: Int) = repository.getById(id)

    override suspend fun add(param: LinkParam) = repository.add(param)

    override suspend fun update(id: Int, param: LinkParam) = repository.update(id, param)

    override suspend fun delete(id: Int) = repository.delete(id)
}
