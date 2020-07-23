package top.jotyy.core.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

data class LinkEntity(
    val linkId: Int,
    val linkType: Int,
    val linkName: String,
    val linkUrl: String,
    val linkDescription: String,
    val linkRank: Int,
    val createdAt: DateTime,
    @JsonIgnore val isDeleted: Int
)
