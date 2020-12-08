package top.jotyy.domain.usecases

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate
import top.jotyy.core.functional.Either
import top.jotyy.core.interactor.None
import top.jotyy.core.interactor.UseCase
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.dao.UserDao
import top.jotyy.data.model.request.ConfigRequest
import top.jotyy.data.model.request.InitializeSiteRequest
import top.jotyy.domain.extensions.toFailure

/**
 * Initialize site configuration
 */
class InitializeSite(
    private val configDao: ConfigDao,
    private val userDao: UserDao
) : UseCase<None, InitializeSiteRequest>() {

    override fun run(request: InitializeSiteRequest) = try {
        validate(request) {
            validate(InitializeSiteRequest::siteName).isNotEmpty()
            validate(InitializeSiteRequest::siteUrl).isNotEmpty()
            validate(InitializeSiteRequest::userName).isNotEmpty()
            validate(InitializeSiteRequest::password).isNotEmpty()
        }

        userDao.addUser(userName = request.userName, password = request.password)
        configDao.addConfig(ConfigRequest("site_name", request.siteName))
        configDao.addConfig(ConfigRequest("site_url", request.siteUrl))

        Either.Right(None())
    } catch (e: ConstraintViolationException) {
        Either.Left(e.toFailure())
    }
}
