package top.jotyy.di

import org.koin.dsl.module
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.dao.UserDao
import top.jotyy.domain.usecases.Authenticate
import top.jotyy.domain.usecases.InitializeSite
import top.jotyy.domain.usecases.UpdateConfigByName

/**
 * dependencies module
 */
val appModule = module {
    // dao
    single { ConfigDao() }
    single { UserDao() }

    // use cases
    single { UpdateConfigByName(get()) }
    single { Authenticate(get()) }
    single { InitializeSite(get(), get()) }
}
