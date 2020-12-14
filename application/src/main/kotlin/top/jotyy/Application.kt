package top.jotyy

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.Koin
import top.jotyy.data.database.initDatabase
import top.jotyy.di.appModule
import top.jotyy.domain.auth.AppJWT
import top.jotyy.domain.auth.AuthPrincipal
import top.jotyy.router.appRouter
import top.jotyy.router.blogRouter
import top.jotyy.router.configRouter

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        commandLineEnvironment(args)
    ).apply { start(wait = true) }
}

fun Application.module() {

    with(AppConfig(environment.config)) {
        initDatabase(
            host = DATABASE_HOST,
            port = DATABASE_PORT,
            databaseName = DATABASE_NAME,
            user = DATABASE_USER,
            password = DATABASE_PASSWORD
        )
    }

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
        jwt {
            verifier(AppJWT.makeJwtVerifier())
            realm = "Pure Blog"
            validate {
                val userName = it.payload.getClaim(AppJWT.CLAIM_NAME).asString()
                val password = it.payload.getClaim(AppJWT.CLAIM_PWD).asString()
                if (userName != null && password != null) {
                    AuthPrincipal(userName)
                } else {
                    null
                }
            }
        }
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

        appRouter()
        configRouter()
        blogRouter()
    }
}
