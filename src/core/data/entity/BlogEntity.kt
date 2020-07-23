package top.jotyy.core.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

data class BlogEntity(
    val blogId: Int,
    val blogTitle: String,
    val blogSubUrl: String,
    val blogCoverImage: String,
    val blogContent: String,
    val blogCategoryId: Int,
    val blogCategoryName: String,
    val blogTags: String,
    val blogStatus: Int,
    val blogViews: Int,
    val enableComment: Int,
    @JsonIgnore val isDeleted: Int,
    val createdAt: DateTime,
    val updatedAt: DateTime
)
