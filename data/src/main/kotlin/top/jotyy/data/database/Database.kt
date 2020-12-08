package top.jotyy.data.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.Blogs
import top.jotyy.data.database.table.Categories
import top.jotyy.data.database.table.Comments
import top.jotyy.data.database.table.Configs
import top.jotyy.data.database.table.Links
import top.jotyy.data.database.table.Relations
import top.jotyy.data.database.table.Tags
import top.jotyy.data.database.table.Users

fun initDatabase() {
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
