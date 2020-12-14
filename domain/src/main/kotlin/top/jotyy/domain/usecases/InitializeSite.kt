package top.jotyy.domain.usecases

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.dao.UserDao
import top.jotyy.data.model.request.InitializeSiteRequest
import top.jotyy.domain.extensions.toFailure

/**
 * UseCase of site initialization
 */
class InitializeSite(
    private val configDao: ConfigDao,
    private val userDao: UserDao
) : UseCase<None, InitializeSiteRequest>() {

    override fun run(request: InitializeSiteRequest) = try {
        validate(request) {
            validate(InitializeSiteRequest::siteName).hasSize(min = 2, max = 20)
            validate(InitializeSiteRequest::siteUrl).isNotEmpty()
            validate(InitializeSiteRequest::userName).hasSize(min = 2, max = 20)
            validate(InitializeSiteRequest::password).hasSize(min = 6, max = 20)
        }

        userDao.addUser(userName = request.userName, password = request.password)
        configDao.addConfig("site_name", request.siteName)
        configDao.addConfig("site_url", request.siteUrl)

        Either.Right(None())
    } catch (e: ConstraintViolationException) {
        Either.Left(e.toFailure())
    }
}
