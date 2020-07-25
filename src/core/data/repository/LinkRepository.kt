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
import top.jotyy.core.data.entity.LinkEntity
import top.jotyy.core.data.param.LinkParam
import top.jotyy.core.data.table.Links
import top.jotyy.core.exception.GenericError

class LinkRepository : IRepository<LinkEntity, LinkParam> {

    override suspend fun getAll(): Result<List<LinkEntity>> = dbExecute({
        Links.selectAll().map { toEntity(it) }.toList()
    }) { emptyOrNot(it) }

    override suspend fun getById(id: Int): Result<LinkEntity> = dbExecute({
        Links.select { Links.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }

    override suspend fun add(param: LinkParam): Result<LinkEntity> =
        dbExecute({
            Links.insert {
                it[type] = param.linkType
                it[name] = param.linkName
                it[url] = param.linkUrl
                it[description] = param.linkDescription
            }
            Links.select { Links.name eq param.linkName }
                .mapNotNull { toEntity(it) }
                .singleOrNull()
        }) { emptyOrNot(it) }

    override suspend fun update(id: Int, param: LinkParam): Result<LinkEntity> = dbExecute({
        Links.update({ Links.id eq id }) {
            it[type] = param.linkType
            it[name] = param.linkName
            it[url] = param.linkUrl
            it[description] = param.linkDescription
        }
        Links.select { Links.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }


    override suspend fun delete(id: Int): Result<String> = dbExecute({
        Links.deleteWhere { Links.id eq id }
    }) {
        if (it == 0) {
            return@dbExecute Failure(GenericError())
        } else {
            return@dbExecute Success("Deleted success")
        }
    }

    private fun toEntity(row: ResultRow): LinkEntity = LinkEntity(
        linkId = row[Links.id],
        linkType = row[Links.type],
        linkName = row[Links.name],
        linkUrl = row[Links.url],
        linkDescription = row[Links.description],
        linkRank = row[Links.rank],
        createdAt = row[Links.createdAt],
        isDeleted = row[Links.isDeleted]
    )
}
