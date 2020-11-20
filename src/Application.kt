package top.jotyy

import com.fasterxml.jackson.databind.SerializationFeature
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.KoinApplicationStarted
import org.koin.ktor.ext.KoinApplicationStopPreparing
import org.koin.ktor.ext.KoinApplicationStopped
import top.jotyy.core.data.repository.BlogRepository
import top.jotyy.core.data.repository.CategoryRepository
import top.jotyy.core.data.repository.CommentRepository
import top.jotyy.core.data.repository.ConfigRepository
import top.jotyy.core.data.repository.LinkRepository
import top.jotyy.core.data.repository.TagRepository
import top.jotyy.core.data.table.Blogs
import top.jotyy.core.data.table.Categories
import top.jotyy.core.data.table.Comments
import top.jotyy.core.data.table.Configs
import top.jotyy.core.data.table.Links
import top.jotyy.core.data.table.Relations
import top.jotyy.core.data.table.Tags
import top.jotyy.core.data.table.Users
import top.jotyy.core.service.BlogService
import top.jotyy.core.service.BlogServiceImpl
import top.jotyy.core.service.CategoryService
import top.jotyy.core.service.CategoryServiceImpl
import top.jotyy.core.service.CommentService
import top.jotyy.core.service.CommentServiceImpl
import top.jotyy.core.service.ConfigService
import top.jotyy.core.service.ConfigServiceImpl
import top.jotyy.core.service.LinkService
import top.jotyy.core.service.LinkServiceImpl
import top.jotyy.core.service.TagService
import top.jotyy.core.service.TagServiceImpl
import top.jotyy.core.service.UserService
import top.jotyy.core.service.UserServiceImpl
import top.jotyy.features.blog.blogRoute
import top.jotyy.features.tag.tagRoute
import top.jotyy.features.user.userRouter
import top.jotyy.repository.UserRepository

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        commandLineEnvironment(args)
    ).apply { start(wait = true) }
}

fun Application.module() {
    initDB()

    environment.monitor.subscribe(KoinApplicationStarted) {
        log.info("Koin started")
    }

    install(Koin) {
        modules(appModule)
    }

    environment.monitor.subscribe(KoinApplicationStopPreparing) {
        log.info("Koin stopping...")
    }
    environment.monitor.subscribe(KoinApplicationStopped) {
        log.info("Koin stopped.")
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost()
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO!", contentType = ContentType.Text.Plain)
        }

        userRouter()
        tagRoute()
        blogRoute()
    }
}

/**
 * Dependency Injection
 */
val appModule = module(createdAtStart = true) {
    singleBy<UserService, UserServiceImpl>()
    single { UserRepository() }

    singleBy<TagService, TagServiceImpl>()
    single { TagRepository() }

    singleBy<BlogService, BlogServiceImpl>()
    single { BlogRepository() }

    singleBy<CategoryService, CategoryServiceImpl>()
    single { CategoryRepository() }

    singleBy<CommentService, CommentServiceImpl>()
    single { CommentRepository() }

    singleBy<ConfigService, ConfigServiceImpl>()
    single { ConfigRepository() }

    singleBy<LinkService, LinkServiceImpl>()
    single { LinkRepository() }
}

/**
 * Database Initialize
 */
fun initDB() {
    val config = HikariConfig("/hikari.properties")
    config.schema = "pure-blog"
    Database.connect(HikariDataSource(config))

    transaction {
        SchemaUtils.create(Users)
        SchemaUtils.create(Blogs)
        SchemaUtils.create(Categories)
        SchemaUtils.create(Tags)
        SchemaUtils.create(Configs)
        SchemaUtils.create(Comments)
        SchemaUtils.create(Links)
        SchemaUtils.create(Relations)
    }
}
