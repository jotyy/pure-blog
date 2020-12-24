package top.jotyy.data.dao

import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import top.jotyy.data.database.table.LinkEntity
import top.jotyy.data.model.response.LinkResponse

/**
 * Link DAO
 */
class LinkDao {

    /**
     * Add link
     *
     * @param type link type
     * @param name link name
     * @param url link url
     * @param description link description
     */
    fun addLink(type: Int, name: String, url: String, description: String) = transaction {
        LinkEntity.new {
            this.type = type
            this.name = name
            this.url = url
            this.description = description
        }.let { LinkResponse.fromEntity(it) }
    }

    /**
     * Delete link by id
     *
     * @param id link id
     */
    fun deleteLinkById(id: Int) = transaction {
        LinkEntity.findById(id)?.delete()
    }

    /**
     * Update link
     *
     * @param id link id
     * @param type link type
     * @param name link name
     * @param url link url
     * @param description link description
     */
    fun updateLink(id: Int, type: Int?, name: String?, url: String?, description: String?) = transaction {
        LinkEntity.findById(id)?.apply {
            this.type = type ?: this.type
            this.name = name ?: this.name
            this.url = url ?: this.url
            this.description = description ?: this.description
            this.updatedAt = DateTime()
        }?.let { LinkResponse.fromEntity(it) }
    }

    /**
     * Get all links
     */
    fun getLinks() = transaction {
        LinkEntity.all().map { LinkResponse.fromEntity(it) }
    }

    /**
     * Get link by id
     *
     * @param id link id
     */
    fun getLinkById(id: Int) = transaction {
        LinkEntity.findById(id)?.let { LinkResponse.fromEntity(it) }
    }
}
