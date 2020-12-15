package top.jotyy.domain.usecases

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.validate
import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.NotFoundFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.BlogDao
import top.jotyy.data.dao.CategoryDao
import top.jotyy.data.model.request.UpdateBlogRequest
import top.jotyy.data.model.response.BlogResponse
import top.jotyy.domain.extensions.toFailure

/**
 * Update blog
 */
class UpdateBlog(
    private val blogDao: BlogDao,
    private val categoryDao: CategoryDao
) : UseCase<BlogResponse, UpdateBlogRequest>() {
    override fun run(request: UpdateBlogRequest): Either<Failure, BlogResponse> = try {
        validate(request) {
            validate(UpdateBlogRequest::title).hasSize(max = 20)
            validate(UpdateBlogRequest::subUrl).hasSize(max = 20)
            validate(UpdateBlogRequest::coverImage).hasSize(max = 20)
        }

        checkIfExistOrThrowException(request.id)
        checkIfCategoryExistOrThrowException(request.categoryId)

        val response = blogDao.updateBlog(
            id = request.id,
            title = request.title,
            subUrl = request.subUrl,
            coverImage = request.coverImage,
            content = request.content,
            categoryId = request.categoryId,
            categoryName = request.categoryName,
            tags = request.tags.toString(),
            status = request.status,
            enableComment = request.enableComment
        )
        Either.Right(response!!)
    } catch (e: ConstraintViolationException) {
        Either.Left(e.toFailure())
    } catch (e: NotFoundException) {
        Either.Left(NotFoundFailure(e.message))
    }

    private fun checkIfCategoryExistOrThrowException(categoryId: Int?) {
        categoryId?.let {
            if (!categoryDao.isCategoryExist(it)) {
                throw NotFoundException(item = "category")
            }
        }
    }

    private fun checkIfExistOrThrowException(id: Int) {
        if (blogDao.getBlogById(id) == null) {
            throw NotFoundException(item = "blog")
        }
    }
}
