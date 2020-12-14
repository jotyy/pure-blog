package top.jotyy.data.dao

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.TagEntity

/**
 * DAO of tags
 */
class TagDao {

    /**
     * Add a new tag
     *
     * @param name tag name
     */
    fun addTag(name: String) = transaction {
        TagEntity.new {
            this.name = name
        }
    }

    /**
     * Delete tag
     *
     * @param id tag id
     */
    fun deleteTag(id: Int) = transaction {
        TagEntity.findById(id)?.delete()
    }

    /**
     * Update tag
     *
     * @param id tag id
     * @param name tag name
     */
    fun updateTag(id: Int, name: String) = transaction {
        TagEntity.findById(id)?.name = name
    }

    /**
     * Get all tags
     */
    fun getTags() = transaction {
        TagEntity.all()
    }
}
