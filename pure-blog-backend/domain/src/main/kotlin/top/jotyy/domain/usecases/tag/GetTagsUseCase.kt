package top.jotyy.domain.usecases.tag

import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.toFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.TagDao
import top.jotyy.data.model.response.TagResponse

/**
 * Get all tags
 */
class GetTagsUseCase(
    private val tagDao: TagDao
) : UseCase<List<TagResponse>, None>() {
    override fun run(request: None): Either<Failure, List<TagResponse>> = try {
        val response = tagDao.getTags()
        if (response.isEmpty()) {
            throw NotFoundException("tags")
        } else {
            Either.Right(response)
        }
    } catch (nfe: NotFoundException) {
        Either.Left(nfe.toFailure())
    }
}
