package top.jotyy.domain.usecases.category

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.validate
import top.jotyy.core.exception.AlreadyUsedException
import top.jotyy.core.exception.Failure
import top.jotyy.core.exception.toFailure
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.CategoryDao
import top.jotyy.data.model.request.AddCategoryRequest
import top.jotyy.data.model.response.CategoryResponse
import top.jotyy.domain.extensions.toFailure

/**
 * Add category
 */
class AddCategoryUseCase(
    private val categoryDao: CategoryDao
) : UseCase<CategoryResponse, AddCategoryRequest>() {
    override fun run(request: AddCategoryRequest): Either<Failure, CategoryResponse> = try {
        validate(request) {
            validate(AddCategoryRequest::name).hasSize(min = 2, max = 20)
        }

        checkIfNameAvailableOrThrowException(request.name)
        val response = categoryDao.addCategory(request.name, request.icon)
        Either.Right(response)
    } catch (cve: ConstraintViolationException) {
        Either.Left(cve.toFailure())
    } catch (aue: AlreadyUsedException) {
        Either.Left(aue.toFailure())
    }

    private fun checkIfNameAvailableOrThrowException(name: String) {
        if (!categoryDao.isCategoryNameAvailable(name)) {
            throw AlreadyUsedException("category")
        }
    }
}
