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
import top.jotyy.core.data.entity.BlogEntity
import top.jotyy.core.data.param.BlogParam
import top.jotyy.core.data.table.Blogs
import top.jotyy.core.exception.GenericError

class BlogRepository : IRepository<BlogEntity, BlogParam> {

    override suspend fun getAll(): Result<List<BlogEntity>> = dbExecute({
        Blogs.selectAll().map { toEntity(it) }.toList()
    }) { emptyOrNot(it) }

    override suspend fun getById(id: Int): Result<BlogEntity> = dbExecute({
        Blogs.select { Blogs.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }

    override suspend fun add(param: BlogParam): Result<BlogEntity> =
        dbExecute({
            Blogs.insert {
                it[title] = param.blogTitle
                it[subUrl] = param.blogSubUrl ?: ""
                it[coverImage] = param.blogCoverImage ?: ""
                it[content] = param.blogContent
                it[categoryId] = param.blogCategoryId
                it[categoryName] = param.blogCategoryName
                it[tags] = param.blogTags
                it[enableComment] = param.enableComment
            }
            Blogs.select { Blogs.title eq param.blogTitle }
                .mapNotNull { toEntity(it) }
                .singleOrNull()
        }) { emptyOrNot(it) }

    override suspend fun update(id: Int, param: BlogParam): Result<BlogEntity> = dbExecute({
        Blogs.update({ Blogs.id eq id }) {
            it[title] = param.blogTitle
            it[subUrl] = param.blogSubUrl ?: ""
            it[coverImage] = param.blogCoverImage ?: ""
            it[content] = param.blogContent
            it[categoryId] = param.blogCategoryId
            it[categoryName] = param.blogCategoryName
            it[tags] = param.blogTags
            it[enableComment] = param.enableComment
        }
        Blogs.select { Blogs.id eq id }
            .mapNotNull { toEntity(it) }
            .singleOrNull()
    }) { emptyOrNot(it) }


    override suspend fun delete(id: Int): Result<String> = dbExecute({
        Blogs.deleteWhere { Blogs.id eq id }
    }) {
        if (it == 0) {
            return@dbExecute Failure(GenericError())
        } else {
            return@dbExecute Success("Deleted success")
        }
    }

    private fun toEntity(row: ResultRow): BlogEntity = BlogEntity(
        blogId = row[Blogs.id],
        blogTitle = row[Blogs.title],
        blogSubUrl = row[Blogs.subUrl],
        blogCoverImage = row[Blogs.coverImage],
        blogContent = row[Blogs.content],
        blogCategoryId = row[Blogs.categoryId],
        blogCategoryName = row[Blogs.categoryName],
        blogTags = row[Blogs.tags],
        blogStatus = row[Blogs.status],
        blogViews = row[Blogs.views],
        enableComment = row[Blogs.enableComment],
        createdAt = row[Blogs.createdAt],
        isDeleted = row[Blogs.isDeleted],
        updatedAt = row[Blogs.updatedAt]
    )
}
