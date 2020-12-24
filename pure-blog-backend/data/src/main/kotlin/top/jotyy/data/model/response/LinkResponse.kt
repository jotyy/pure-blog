package top.jotyy.data.model.response

import top.jotyy.data.database.table.LinkEntity

data class LinkResponse(
    val id: Int,
    val type: Int,
    val name: String,
    val url: String,
    val description: String,
    val rank: Int
) {
    companion object {
        fun fromEntity(entity: LinkEntity) = LinkResponse(
            id = entity.id.value,
            type = entity.type,
            name = entity.name,
            url = entity.url,
            description = entity.description,
            rank = entity.rank
        )
    }
}
