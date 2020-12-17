package top.jotyy.domain.usecases.tag

import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.toFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.TagDao

/**
 * Remove tag
 */
class RemoveTagUseCase(
    private val tagDao: TagDao
) : UseCase<None, Int>() {
    override fun run(request: Int): Either<Failure, None> = try {
        checkIfExistOrThrowException(request)
        tagDao.deleteTag(request)
        Either.Right(None())
    } catch (nfe: NotFoundException) {
        Either.Left(nfe.toFailure())
    }

    private fun checkIfExistOrThrowException(id: Int) {
        if (!tagDao.isTagExist(id)) {
            throw NotFoundException("tag")
        }
    }
}
