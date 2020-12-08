package top.jotyy.di

import org.koin.dsl.module
import top.jotyy.data.dao.ConfigDao
import top.jotyy.domain.usecases.UpdateConfigByName

val configModule = module {
    single { ConfigDao() }
    single { UpdateConfigByName(get()) }
}
