package top.jotyy.features.blog

import core.constants.BLOG_PATH
import io.ktor.routing.*

fun Routing.blogRoute() {

    get(BLOG_PATH) {
    }

    get("$BLOG_PATH/{id}") {
    }

    post(BLOG_PATH) {
    }

    put(BLOG_PATH) {
    }

    delete(BLOG_PATH) {
    }
}
