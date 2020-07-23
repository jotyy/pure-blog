package top.jotyy.core.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

data class CommentEntity(
    val commentId: Int,
    val blogId: Int,
    val commentator: String,
    val email: String,
    val websiteUrl: String,
    val commentBy: String,
    val commentCreatedBy: String,
    val commentCreatedAt: DateTime,
    val commentatorIp: String,
    val replyBody: String,
    val replyCreatedAt: String,
    val commentStatus: Int,
    @JsonIgnore val isDeleted: Int
)
