package top.jotyy.domain.usecases.category

import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.NotFoundException
import top.jotyy.core.exception.toFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.CategoryDao
import top.jotyy.data.model.response.CategoryResponse

/**
 * Get all categories
 */
class GetCatgoriesUseCase(
    private val categoryDao: CategoryDao
) : UseCase<List<CategoryResponse>, None>() {
    override fun run(request: None): Either<Failure, List<CategoryResponse>> = try {
        val response = categoryDao.getCategories()
        if (response.isEmpty()) {
            throw NotFoundException("categories")
        } else {
            Either.Right(response)
        }
    } catch (nfe: NotFoundException) {
        Either.Left(nfe.toFailure())
    }
}
