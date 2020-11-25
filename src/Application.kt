package top.jotyy

import com.fasterxml.jackson.databind.SerializationFeature
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import core.data.repository.UserRepository
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
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.KoinApplicationStarted
import org.koin.ktor.ext.KoinApplicationStopPreparing
import org.koin.ktor.ext.KoinApplicationStopped
import top.jotyy.core.data.table.Blogs
import top.jotyy.core.data.table.Categories
import top.jotyy.core.data.table.Comments
import top.jotyy.core.data.table.Configs
import top.jotyy.core.data.table.Links
import top.jotyy.core.data.table.Relations
import top.jotyy.core.data.table.Tags
import top.jotyy.core.data.table.Users
import top.jotyy.features.blog.blogRoute
import top.jotyy.features.tag.tagRoute
import top.jotyy.features.user.userRouter

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
    single { UserRepository() }
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
