package top.jotyy.domain.domain.blog

import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.toFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.BlogDao

/**
 * Delete blog
 */
class RemoveBlogUseCase(
    private val blogDao: BlogDao
) : UseCase<None, Int>() {
    override fun run(request: Int): Either<Failure, None> = try {
        if (blogDao.getBlogById(request) != null) {
            blogDao.deleteBlog(request)
        } else {
            throw NotFoundException(item = "blog")
        }
        Either.Right(None())
    } catch (nfe: NotFoundException) {
        Either.Left(nfe.toFailure())
    }
}
