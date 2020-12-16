package top.jotyy.di

import org.koin.dsl.module
import top.jotyy.data.dao.BlogDao
import top.jotyy.data.dao.CategoryDao
import top.jotyy.data.dao.ConfigDao
import top.jotyy.data.dao.UserDao
import top.jotyy.domain.domain.auth.AuthUseCase
import top.jotyy.domain.domain.blog.RemoveBlogUseCase
import top.jotyy.domain.domain.blog.GetBlogsUseCase
import top.jotyy.domain.domain.blog.PostBlogUseCase
import top.jotyy.domain.domain.blog.UpdateBlogUseCase
import top.jotyy.domain.usecases.config.InitializeSiteUseCase
import top.jotyy.domain.usecases.config.UpdateConfigUseCase

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
    single { UpdateConfigUseCase(get()) }
    single { AuthUseCase(get()) }
    single { InitializeSiteUseCase(get(), get()) }
    single { PostBlogUseCase(get(), get()) }
    single { RemoveBlogUseCase(get()) }
    single { UpdateBlogUseCase(get(), get()) }
    single { GetBlogsUseCase(get()) }
}
