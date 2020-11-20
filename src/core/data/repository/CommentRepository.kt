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
import top.jotyy.core.data.entity.CommentEntity
import top.jotyy.core.data.param.CommentParam
import top.jotyy.core.data.table.Comments
import top.jotyy.core.exception.GenericError

class CommentRepository : IRepository<CommentEntity, CommentParam> {

    override suspend fun getAll(): Result<List<CommentEntity>> = dbExecute({
        Comments.selectAll().map { toEntity(it) }.toList()
    }) { emptyOrNot(it) }

    override suspend fun getById(id: Int): Result<CommentEntity> = dbExecute({
        Comments.select { Comments.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }

    override suspend fun add(param: CommentParam): Result<CommentEntity> =
        dbExecute({
            val id = Comments.insert {
                it[commentator] = param.commentator
                it[email] = param.email
                it[websiteUrl] = param.webSiteUrl
                it[commentatorIp] = param.commentatorIp
                it[commentBy] = param.commentBy
            }[Comments.id]
            Comments.select { Comments.id eq id }
                .mapNotNull { toEntity(it) }
                .singleOrNull()
        }) { emptyOrNot(it) }

    override suspend fun update(id: Int, param: CommentParam): Result<CommentEntity> = dbExecute({
        Comments.update({ Comments.id eq id }) {
            it[commentator] = param.commentator
            it[email] = param.email
            it[websiteUrl] = param.webSiteUrl
            it[commentatorIp] = param.commentatorIp
            it[commentBy] = param.commentBy
        }
        Comments.select { Comments.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }

    override suspend fun delete(id: Int): Result<String> = dbExecute({
        Comments.deleteWhere { Comments.id eq id }
    }) {
        if (it == 0) {
            return@dbExecute Failure(GenericError())
        } else {
            return@dbExecute Success("Deleted success")
        }
    }

    private fun toEntity(row: ResultRow): CommentEntity = CommentEntity(
        commentId = row[Comments.id],
        blogId = row[Comments.blogId],
        commentator = row[Comments.commentator],
        email = row[Comments.email],
        websiteUrl = row[Comments.websiteUrl],
        commentBody = row[Comments.commentBody],
        commentBy = row[Comments.commentBy],
        commentatorIp = row[Comments.commentatorIp],
        replyBody = row[Comments.replyBody],
        commentCreatedAt = row[Comments.commentCreatedAt],
        replyCreatedAt = row[Comments.replyCreatedAt],
        commentStatus = row[Comments.commentStatus],
        isDeleted = row[Comments.isDeleted]
    )
}
