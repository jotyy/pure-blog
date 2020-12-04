package top.jotyy

import com.apurebase.kgraphql.GraphQL
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

    install(GraphQL) {
        playground = true
        schema {
            query("hello") {
                resolver { ->
                    "World"
                }
            }
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO, I'm jotyy!", contentType = ContentType.Text.Plain)
        }

        userRouter()
        tagRoute()
        blogRoute()
    }
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
