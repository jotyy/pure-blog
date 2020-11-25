package top.jotyy.core.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

data class Comment(
    val commentId: Int,
    val blogId: Int,
    val commentator: String,
    val email: String,
    val websiteUrl: String,
    val commentBody: String,
    val commentBy: String,
    val commentCreatedAt: DateTime,
    val commentatorIp: String,
    val replyBody: String,
    val replyCreatedAt: DateTime,
    val commentStatus: Int,
    @JsonIgnore val isDeleted: Int
)
