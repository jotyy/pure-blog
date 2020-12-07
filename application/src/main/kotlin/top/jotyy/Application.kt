package top.jotyy

import com.fasterxml.jackson.databind.SerializationFeature
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
import org.koin.dsl.module
import org.koin.ktor.ext.Koin

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        commandLineEnvironment(args)
    ).apply { start(wait = true) }
}

fun Application.module() {
    initDB()

    install(Koin) {
        modules(appModule)
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
            call.respondText("✨Hello, I'm jotyy!", contentType = ContentType.Text.Plain)
        }

//        configRouter()
//        userRouter()
//        tagRoute()
//        blogRoute()
    }
}

/**
 * DI
 */
val appModule = module(createdAtStart = true) {
//    single { ConfigDao() }
//    single { UpdateConfigByName(get()) }
//    single { ConfigRepository(get()) }
}

/**
 * Database Initialize
 */
fun initDB() {
//    val config = HikariConfig("/hikari.properties")
//    config.schema = "pure-blog"
//    Database.connect(HikariDataSource(config))
//
//    transaction {
//        SchemaUtils.create(Users)
//        SchemaUtils.create(Blogs)
//        SchemaUtils.create(Categories)
//        SchemaUtils.create(Tags)
//        SchemaUtils.create(Configs)
//        SchemaUtils.create(Comments)
//        SchemaUtils.create(Links)
//        SchemaUtils.create(Relations)
//    }
}
