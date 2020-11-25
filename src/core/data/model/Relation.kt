package top.jotyy.core.data.model

import org.joda.time.DateTime

data class Relation(
    val relationId: Int,
    val blogId: Int,
    val tagId: Int,
    val createdAt: DateTime
)
