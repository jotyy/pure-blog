package top.jotyy.data.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime
import top.jotyy.core.constants.LENGTH_100
import top.jotyy.core.constants.LENGTH_50

/**
 * Link Table
 *
 * @author Jotyy
 */
object Links : Table("tb_link") {
    val id = integer("link_id")
    val type = integer("link_type")
    val name = varchar("link_name", LENGTH_50)
    val url = varchar("link_url", LENGTH_100)
    val description = varchar("link_description", LENGTH_100)
    val rank = integer("link_rank")
    val isDeleted = integer("is_deleted")
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id, name = "link_id")
}
