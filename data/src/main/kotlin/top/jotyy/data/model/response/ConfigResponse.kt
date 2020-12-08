package top.jotyy.data.model.response

data class Config(
    val name: String,
    val value: String
)

data class ConfigsResponse(
    override val status: State,
    override val message: String,
    val configs: List<Config> = emptyList()
) : Response {
    companion object {
        fun success(configs: List<Config>) = ConfigsResponse(
            State.SUCCESS,
            "Get configs success",
            configs
        )

        fun failure(message: String) = ConfigsResponse(
            State.FAILED,
            message
        )
    }
}

data class ConfigResponse(
    override val status: State,
    override val message: String,
    val config: Config?
) : Response {
    companion object {
        fun success(config: Config) = ConfigResponse(
            State.SUCCESS,
            "Get config success",
            config
        )

        fun failure(message: String) = ConfigResponse(
            State.FAILED,
            message,
            null
        )
    }
}
