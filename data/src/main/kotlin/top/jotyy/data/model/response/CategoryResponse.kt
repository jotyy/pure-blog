package top.jotyy.data.model.response

import org.joda.time.DateTime
import top.jotyy.data.database.table.CategoryEntity

data class CategoryResponse(
    val categoryId: Int,
    val categoryName: String,
    val categoryIcon: String?,
    val categoryRank: Int,
    val createdAt: DateTime,
) {

    companion object {
        fun fromEntity(entity: CategoryEntity) = CategoryResponse(
            categoryId = entity.id.value,
            categoryName = entity.name,
            categoryIcon = entity.icon,
            categoryRank = entity.rank,
            createdAt = entity.createdAt
        )
    }
}
