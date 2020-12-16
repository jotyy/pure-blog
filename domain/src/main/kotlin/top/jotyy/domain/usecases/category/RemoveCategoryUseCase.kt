package top.jotyy.domain.usecases.category

import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.NotFoundFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.CategoryDao

/**
 * Remove category
 */
class RemoveCategoryUseCase(
    private val categoryDao: CategoryDao
) : UseCase<None, Int>() {
    override fun run(request: Int): Either<Failure, None> = try {
        checkIfCategoryExistOrThrowException(request)
        categoryDao.deleteCategory(request)
        Either.Right(None())
    } catch (e: NotFoundException) {
        Either.Left(NotFoundFailure(e.message))
    }

    private fun checkIfCategoryExistOrThrowException(id: Int) {
        if (!categoryDao.isCategoryExist(id)) {
            throw NotFoundException("category")
        }
    }
}
