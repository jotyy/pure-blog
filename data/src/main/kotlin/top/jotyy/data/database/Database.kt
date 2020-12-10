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
