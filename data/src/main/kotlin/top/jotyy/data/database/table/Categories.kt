package top.jotyy.data.database.table

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime
import top.jotyy.core.constants.LENGTH_50

/**
 * Category Table
 */
object Categories : IntIdTable(name = "tb_category", columnName = "category_id") {
    val name = varchar("category_name", LENGTH_50)
    val icon = varchar("category_icon", LENGTH_50)
    val rank = integer("category_rank")
    val isDeleted = integer("is_deleted").default(0)
    val createdAt = datetime("created_at")
}

class CategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryEntity>(Categories)

    var name by Categories.name
    var icon by Categories.icon
    var rank by Categories.rank
    var isDelete by Categories.isDeleted
    var createdAt by Categories.createdAt
}
