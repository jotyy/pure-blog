package top.jotyy.core.data.entity

import org.joda.time.DateTime

data class RelationEntity(
    val relationId: Int,
    val blogId: Int,
    val tagId: Int,
    val createdAt: DateTime
)
