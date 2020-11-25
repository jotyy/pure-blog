package top.jotyy.features.tag

import core.constants.TAG_PATH
import io.ktor.routing.*

fun Routing.tagRoute() {

    get(TAG_PATH) {
    }

    get("$TAG_PATH/{id}") {
    }

    post(TAG_PATH) {
    }

    put(TAG_PATH) {
    }

    delete(TAG_PATH) {
    }
}
