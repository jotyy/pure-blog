package top.jotyy.domain.usecases

import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.NotFoundFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.BlogDao
import top.jotyy.data.model.request.FetchBlogsRequest
import top.jotyy.data.model.response.BlogResponse

/**
 * Fetch all blogs
 */
class FetchBlogs(
    private val blogDao: BlogDao
) : UseCase<List<BlogResponse>, FetchBlogsRequest>() {
    override fun run(request: FetchBlogsRequest): Either<Failure, List<BlogResponse>> =
        try {
            val response = blogDao.getBlogs()
            if (response.isEmpty()) {
                throw NotFoundException("No blogs founded")
            } else {
                Either.Right(response)
            }
        } catch (e: NotFoundException) {
            Either.Left(NotFoundFailure())
        }
}
