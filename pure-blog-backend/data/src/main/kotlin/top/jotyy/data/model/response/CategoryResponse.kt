package top.jotyy.data.model.response

import top.jotyy.core.extensions.toFormatString
import top.jotyy.data.database.table.CategoryEntity

data class CategoryResponse(
    val categoryId: Int,
    val categoryName: String,
    val categoryIcon: String?,
    val categoryRank: Int,
    val createdAt: String
) {

    companion object {
        fun fromEntity(entity: CategoryEntity) = CategoryResponse(
            categoryId = entity.id.value,
            categoryName = entity.name,
            categoryIcon = entity.icon,
            categoryRank = entity.rank,
            createdAt = entity.createdAt.toDate().toFormatString()
        )
    }
}
