package top.jotyy.core.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

data class TagEntity(
    val tagId: Int,
    val tagName: String,
    val isDeleted: Int,
    @JsonIgnore val createdAt: DateTime
)
