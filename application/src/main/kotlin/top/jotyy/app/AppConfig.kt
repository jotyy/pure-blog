package top.jotyy.app

import io.ktor.config.*

/**
 * Class containing Configuration values or secret key which will be provided from
 * application.conf (from environment variables).
 */
@Suppress("PropertyName")
class AppConfig constructor(config: ApplicationConfig) {
    val DATABASE_HOST = config.property("database.host").getString()
    val DATABASE_PORT = config.property("database.port").getString()
    val DATABASE_NAME = config.property("database.name").getString()
    val DATABASE_USER = config.property("database.user").getString()
    val DATABASE_PASSWORD = config.property("database.password").getString()
}
