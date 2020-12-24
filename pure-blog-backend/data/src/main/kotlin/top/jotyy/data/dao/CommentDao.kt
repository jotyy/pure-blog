package top.jotyy.data.dao

import org.jetbrains.exposed.sql.transactions.transaction
import top.jotyy.data.database.table.CommentEntity

/**
 * Comment DAO
 */
class CommentDao {

    /**
     * Add Comment
     */
    fun addComment() = transaction {
        CommentEntity.new {

        }
    }
}
