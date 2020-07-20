package top.jotyy.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object Comments : Table("tb_comment") {
    val id = integer("comment_id").autoIncrement()
    val blogId = integer("blog_id")
    val commentator = varchar("commentator", 50)
    val email = varchar("email", 100)
    val websiteUrl = varchar("website_url", 50)
    val commentBody = varchar("comment_by", 200)
    val commentCreatedAt = datetime("comment_created_at")
    val commentatorIp = varchar("commentator_ip", 20)
    val replyBody = varchar("reply_body", 200)
    val replyCreatedAt = datetime("reply_created_at")
    val commentStatus = integer("comment_status").default(0)
    val isDeleted = integer("is_deleted").default(0)
    override val primaryKey = PrimaryKey(id, name = "comment_id")
}