package top.jotyy.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import top.jotyy.bootstrap.LENGTH_50

/**
 * Category Table
 *
 * @author Jotyy
 */
object Categories : Table("tb_category") {
    val id = integer("category_id").autoIncrement()
    val name = varchar("category_name", LENGTH_50)
    val icon = varchar("category_icon", LENGTH_50)
    val rank = integer("category_rank")
    val isDeleted = integer("is_deleted").default(0)
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id, name = "category_id")
}
