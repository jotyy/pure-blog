package top.jotyy.core.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

data class Category(
    val categoryId: Int,
    val categoryName: String,
    val categoryIcon: String,
    val categoryRank: Int,
    val createdAt: DateTime,
    @JsonIgnore val isDeleted: Int
)