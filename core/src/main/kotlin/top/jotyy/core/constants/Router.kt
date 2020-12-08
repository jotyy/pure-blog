package top.jotyy.core.constants

object Router {
    private const val API_PREFIX = "/api/v1"

    const val INIT_PATH = "/init"
    const val LOGIN_PATH = "/login"
    const val USER_PATH = "$API_PREFIX/users"
    const val TAG_PATH = "$API_PREFIX/tags"
    const val BLOG_PATH = "$API_PREFIX/blogs"
    const val CATEGORY_PATH = "$API_PREFIX/categories"
    const val COMMENT_PATH = "$API_PREFIX/comments"
    const val LINK_PATH = "$API_PREFIX/links"
    const val CONFIG_PATH = "$API_PREFIX/configs"
}
