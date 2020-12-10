package top.jotyy.data.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.*

fun initDatabase(
    host: String,
    port: String,
    databaseName: String,
    user: String,
    password: String
) {
    Database.connect(
        url = "jdbc:postgresql://$host:$port/$databaseName",
        driver = "org.postgresql.Driver",
        user = user,
        password = password
    )

    val tables = arrayOf(Users, Blogs, Categories, Tags, Configs, Comments, Links, Relations)

    transaction {
        SchemaUtils.createMissingTablesAndColumns(*tables)
    }
}
