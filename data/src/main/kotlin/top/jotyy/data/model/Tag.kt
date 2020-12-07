package top.jotyy.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

data class Tag(
    val tagId: Int,
    val tagName: String,
    val isDeleted: Int,
    @JsonIgnore val createdAt: DateTime
)
