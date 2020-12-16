package top.jotyy.domain.domain.blog

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.validate
import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.NotFoundFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.BlogDao
import top.jotyy.data.dao.CategoryDao
import top.jotyy.data.model.request.PostBlogRequest
import top.jotyy.domain.extensions.toFailure

/**
 * Post a new blog
 */
class PostBlogUseCase(
    private val blogDao: BlogDao,
    private val categoryDao: CategoryDao
) : UseCase<None, PostBlogRequest>() {
    override fun run(request: PostBlogRequest): Either<Failure, None> {
        try {
            validate(request) {
                validate(PostBlogRequest::title).hasSize(min = 1, max = 20)
                validate(PostBlogRequest::subUrl).hasSize(min = 1, max = 20)
                validate(PostBlogRequest::coverImage).hasSize(min = 0, max = 20)
                validate(PostBlogRequest::content).hasSize(min = 1)
            }

            checkIfCategoryExistOrThrowException(request.categoryId)

            blogDao.addBlog(
                title = request.title,
                subUrl = request.subUrl,
                coverImage = request.coverImage,
                content = request.content,
                categoryId = request.categoryId,
                categoryName = request.categoryName,
                tags = if (request.tags.isNullOrEmpty()) null else request.tags.toString(),
                status = request.status,
                enableComment = request.enableComment
            )
            return Either.Right(None())
        } catch (e: ConstraintViolationException) {
            return Either.Left(e.toFailure())
        } catch (e: NotFoundException) {
            return Either.Left(NotFoundFailure(e.message))
        }
    }

    private fun checkIfCategoryExistOrThrowException(categoryId: Int?) {
        categoryId?.let {
            if (!categoryDao.isCategoryExist(it)) {
                throw NotFoundException(item = "category")
            }
        }
    }
}
