package top.jotyy.core.service

import top.jotyy.core.abstraction.IService
import top.jotyy.core.data.entity.BlogEntity
import top.jotyy.core.data.param.BlogParam
import top.jotyy.core.data.repository.BlogRepository

interface BlogService : IService<BlogEntity, BlogParam>

class BlogServiceImpl(private val repository: BlogRepository) : BlogService {
    override suspend fun getAll() = repository.getAll()

    override suspend fun getById(id: Int) = repository.getById(id)

    override suspend fun add(param: BlogParam) = repository.add(param)

    override suspend fun update(id: Int, param: BlogParam) = repository.update(id, param)

    override suspend fun delete(id: Int) = repository.delete(id)
}
