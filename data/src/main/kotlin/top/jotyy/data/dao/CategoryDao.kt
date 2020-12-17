package top.jotyy.data.dao

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.Categories
import top.jotyy.data.database.table.CategoryEntity
import top.jotyy.data.model.response.CategoryResponse

/**
 * Category DAO layer
 */
class CategoryDao {

    /**
     * Add a category
     *
     * @param name category name
     * @param icon category icon path
     */
    fun addCategory(name: String, icon: String? = null) = transaction {
        CategoryEntity.new {
            this.name = name
            this.icon = icon
        }.let {
            CategoryResponse.fromEntity(it)
        }
    }

    /**
     * Delete a category
     *
     * @param id category id
     */
    fun deleteCategory(id: Int) = transaction {
        CategoryEntity.findById(id)?.delete()
    }

    /**
     * Update a category
     *
     * @param id category id
     * @param name category name
     * @param icon category icon path
     */
    fun updateCategory(id: Int, name: String?, icon: String?) = transaction {
        CategoryEntity.findById(id)?.apply {
            this.name = name ?: this.name
            this.icon = icon ?: this.icon
        }?.let { CategoryResponse.fromEntity(it) }
    }

    /**
     * Update a category rank
     *
     * @param id category id
     * @param rank category rank
     */
    fun updateCategoryRankById(id: Int, rank: Int) = transaction {
        CategoryEntity.findById(id)?.apply {
            this.rank = rank
        }?.let { CategoryResponse.fromEntity(it) }
    }

    /**
     * Get all categories
     */
    fun getCategories() = transaction {
        CategoryEntity.all()
            .map { CategoryResponse.fromEntity(it) }
    }

    /**
     * Get category by name
     *
     * @param name Name of category
     */
    fun getCategoryByName(name: String): CategoryResponse? = transaction {
        CategoryEntity.find {
            Categories.name eq name
        }.firstOrNull()?.let { CategoryResponse.fromEntity(it) }
    }

    /**
     * Check if category name available
     *
     * @param name Category name
     */
    fun isCategoryNameAvailable(name: String) = transaction {
        CategoryEntity.find { Categories.name eq name }.firstOrNull() != null
    }

    /**
     * Check if category is exist
     *
     * @param id Category id
     */
    fun isCategoryExist(id: Int) = transaction {
        CategoryEntity.findById(id) != null
    }
}
