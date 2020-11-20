package top.jotyy.features.blog

import core.data.constants.BLOG_PATH
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import top.jotyy.core.abstraction.handleResult
import top.jotyy.core.service.BlogService

fun Routing.blogRoute() {
    val service by inject<BlogService>()

    get(BLOG_PATH) {
        handleResult(service.getAll())
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
