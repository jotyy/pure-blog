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
import top.jotyy.core.data.entity.CategoryEntity
import top.jotyy.core.data.param.CategoryParam
import top.jotyy.core.data.table.Categories
import top.jotyy.core.exception.GenericError

class CategoryRepository : IRepository<CategoryEntity, CategoryParam> {

    override suspend fun getAll(): Result<List<CategoryEntity>> = dbExecute({
        Categories.selectAll().map { toEntity(it) }.toList()
    }) { emptyOrNot(it) }

    override suspend fun getById(id: Int): Result<CategoryEntity> = dbExecute({
        Categories.select { Categories.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }

    override suspend fun add(param: CategoryParam): Result<CategoryEntity> =
        dbExecute({
            Categories.insert {
                it[name] = param.categoryName
                it[icon] = param.categoryIcon
            }
            Categories.select { Categories.name eq param.categoryName }
                .mapNotNull { toEntity(it) }
                .singleOrNull()
        }) { emptyOrNot(it) }

    override suspend fun update(id: Int, param: CategoryParam): Result<CategoryEntity> = dbExecute({
        Categories.update({ Categories.id eq id }) {
            it[name] = param.categoryName
            it[icon] = param.categoryIcon
        }
        Categories.select { Categories.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }


    override suspend fun delete(id: Int): Result<String> = dbExecute({
        Categories.deleteWhere { Categories.id eq id }
    }) {
        if (it == 0) {
            return@dbExecute Failure(GenericError())
        } else {
            return@dbExecute Success("Deleted success")
        }
    }

    private fun toEntity(row: ResultRow): CategoryEntity = CategoryEntity(
        categoryId = row[Categories.id],
        categoryName = row[Categories.name],
        categoryIcon = row[Categories.icon],
        categoryRank = row[Categories.rank],
        isDeleted = row[Categories.isDeleted],
        createdAt = row[Categories.createdAt]
    )
}
