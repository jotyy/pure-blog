package top.jotyy.core.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import top.jotyy.bootstrap.LENGTH_100
import top.jotyy.bootstrap.LENGTH_20
import top.jotyy.bootstrap.LENGTH_200
import top.jotyy.bootstrap.LENGTH_50

/**
 * Comment Table
 *
 * @author Jotyy
 */
object Comments : Table("tb_comment") {
    val id = integer("comment_id").autoIncrement()
    val blogId = integer("blog_id")
    val commentator = varchar("commentator", LENGTH_50)
    val email = varchar("email", LENGTH_100)
    val websiteUrl = varchar("website_url", LENGTH_50)
    val commentBody = varchar("comment_body", LENGTH_200)
    val commentBy = varchar("comment_by", LENGTH_50)
    val commentCreatedAt = datetime("comment_created_at")
    val commentatorIp = varchar("commentator_ip", LENGTH_20)
    val replyBody = varchar("reply_body", LENGTH_200)
    val replyCreatedAt = datetime("reply_created_at")
    val commentStatus = integer("comment_status").default(0)
    val isDeleted = integer("is_deleted").default(0)
    override val primaryKey = PrimaryKey(id, name = "comment_id")
}
