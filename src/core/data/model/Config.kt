package top.jotyy.core.data.model

import top.jotyy.core.data.table.ConfigEntity

data class Config(
    val configName: String,
    val configValue: String
) {
    companion object {
        fun fromEntity(entity: ConfigEntity) = Config(
            configName = entity.name,
            configValue = entity.value
        )
    }
}
