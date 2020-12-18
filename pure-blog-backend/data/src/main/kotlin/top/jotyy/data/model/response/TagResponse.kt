package top.jotyy.data.model.response

import top.jotyy.data.database.table.TagEntity

data class TagResponse(val name: String) {
    companion object {
        fun fromEntity(entity: TagEntity) = TagResponse(name = entity.name)
    }
}
