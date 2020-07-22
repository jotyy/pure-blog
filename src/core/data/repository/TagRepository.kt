package top.jotyy.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import core.abstraction.Failure
import core.exception.GenericError
import core.abstraction.IRepository
import core.abstraction.Result
import core.abstraction.Success
import top.jotyy.core.data.table.Tags
import core.data.entity.TagEntity
import top.jotyy.core.data.param.TagParam

class TagRepository : IRepository<TagEntity, TagParam> {

    override suspend fun getAll(): Result<List<TagEntity>> = dbExecute({
        Tags.selectAll().map { toEntity(it) }.toList()
    }) { emptyOrNot(it) }

    override suspend fun getById(id: Int): Result<TagEntity> = dbExecute({
        Tags.select { Tags.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }

    override suspend fun add(param: TagParam): Result<TagEntity> =
        dbExecute({
            Tags.insert {
                it[name] = param.tagName
            }
            Tags.select { Tags.name eq param.tagName }
                .mapNotNull { toEntity(it) }
                .singleOrNull()
        }) { emptyOrNot(it) }

    override suspend fun update(id: Int, param: TagParam): Result<TagEntity> = dbExecute({
        Tags.update({ Tags.id eq id }) {
            it[name] = param.tagName
        }
        Tags.select { Tags.name eq param.tagName }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }


    override suspend fun delete(id: Int): Result<String> = dbExecute({
        Tags.deleteWhere { Tags.id eq id }
    }) {
        if (it == 0) {
            return@dbExecute Failure(GenericError())
        } else {
            return@dbExecute Success("Deleted success")
        }
    }

    private fun toEntity(row: ResultRow): TagEntity = TagEntity(
        tagId = row[Tags.id],
        tagName = row[Tags.name],
        isDeleted = row[Tags.isDeleted],
        createdAt = row[Tags.createdAt]
    )
}
