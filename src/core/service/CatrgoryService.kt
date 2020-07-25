package top.jotyy.core.service

import top.jotyy.core.abstraction.IService
import top.jotyy.core.data.entity.CategoryEntity
import top.jotyy.core.data.param.CategoryParam
import top.jotyy.core.data.repository.CategoryRepository

interface CategoryService : IService<CategoryEntity, CategoryParam>

class CategoryServiceImpl(private val repository: CategoryRepository) : CategoryService {
    override suspend fun getAll() = repository.getAll()

    override suspend fun getById(id: Int) = repository.getById(id)

    override suspend fun add(param: CategoryParam) = repository.add(param)

    override suspend fun update(id: Int, param: CategoryParam) = repository.update(id, param)

    override suspend fun delete(id: Int) = repository.delete(id)
}
