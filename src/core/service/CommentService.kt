package top.jotyy.core.service

import top.jotyy.core.abstraction.IService
import top.jotyy.core.data.entity.CommentEntity
import top.jotyy.core.data.param.CommentParam
import top.jotyy.core.data.repository.CommentRepository

interface CommentService : IService<CommentEntity, CommentParam>

class CommentServiceImpl(private val repository: CommentRepository) : CommentService {
    override suspend fun getAll() = repository.getAll()

    override suspend fun getById(id: Int) = repository.getById(id)

    override suspend fun add(param: CommentParam) = repository.add(param)

    override suspend fun update(id: Int, param: CommentParam) = repository.update(id, param)

    override suspend fun delete(id: Int) = repository.delete(id)
}
