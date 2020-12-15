package top.jotyy.di

import org.koin.dsl.module
import top.jotyy.data.dao.BlogDao
import top.jotyy.data.dao.CategoryDao
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.dao.UserDao
import top.jotyy.domain.usecases.*

/**
 * Dependencies injection module
 */
val appModule = module {
    // dao
    single { ConfigDao() }
    single { UserDao() }
    single { BlogDao() }
    single { CategoryDao() }

    // use cases
    single { UpdateConfigByName(get()) }
    single { Authenticate(get()) }
    single { InitializeSite(get(), get()) }
    single { PostBlog(get(), get()) }
    single { FetchBlogs(get()) }
    single { DeleteBlog(get()) }
}
