package top.jotyy.data.dao

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.TagEntity
import top.jotyy.data.database.table.Tags

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
        TagEntity.find { Tags.id eq id }
            .first().delete()
    }
}
