package top.jotyy.service

import top.jotyy.abstraction.IService
import top.jotyy.model.entity.TagEntity
import top.jotyy.model.param.TagParam
import top.jotyy.repository.TagRepository

interface TagService : IService<TagEntity, TagParam>

class TagServiceImpl(val repository: TagRepository) : TagService {
    override suspend fun getAll() = repository.getAll()

    override suspend fun getById(id: Int) = repository.getById(id)

    override suspend fun add(param: TagParam) = repository.add(param)

    override suspend fun update(id: Int, param: TagParam) = repository.update(id, param)

    override suspend fun delete(id: Int) = repository.delete(id)
}
