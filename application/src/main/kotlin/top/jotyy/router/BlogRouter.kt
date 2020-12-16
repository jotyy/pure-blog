package top.jotyy.router

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import top.jotyy.core.constants.Router
import top.jotyy.core.functional.onAsyncFailure
import top.jotyy.core.functional.onAsyncSuccess
import top.jotyy.data.model.request.FetchBlogsRequest
import top.jotyy.data.model.request.PostBlogRequest
import top.jotyy.data.model.request.UpdateBlogRequest
import top.jotyy.domain.domain.blog.RemoveBlogUseCase
import top.jotyy.domain.domain.blog.GetBlogsUseCase
import top.jotyy.domain.domain.blog.PostBlogUseCase
import top.jotyy.domain.domain.blog.UpdateBlogUseCase
import top.jotyy.extensions.respondFailure
import top.jotyy.extensions.respondSuccess

/**
 * Blog routers
 */
fun Routing.blogRouter() {
    val postBlog by inject<PostBlogUseCase>()
    val deleteBlog by inject<RemoveBlogUseCase>()
    val updateBlog by inject<UpdateBlogUseCase>()
    val fetchBlogs by inject<GetBlogsUseCase>()

    post(Router.BLOG_PATH) {
        val request = runCatching {
            call.receive<PostBlogRequest>()
        }.getOrElse {
            throw BadRequestException("Request parameters error")
        }
        postBlog(request)
            .onAsyncSuccess {
                respondSuccess(it)
            }
            .onAsyncFailure {
                respondFailure(it)
            }
    }

    delete("${Router.BLOG_PATH}/{id}") {
        val blogId = runCatching { call.parameters["id"] }
            .getOrElse { throw BadRequestException("Request parameters error") }
        deleteBlog(blogId!!.toInt())
            .onAsyncSuccess { respondSuccess(it) }
            .onAsyncFailure { respondFailure(it) }
    }

    put(Router.BLOG_PATH) {
        val request = runCatching {
            call.receive<UpdateBlogRequest>()
        }.getOrElse { throw BadRequestException("Request parameters error") }

        updateBlog(request)
            .onAsyncSuccess { respondSuccess(it) }
            .onAsyncFailure { respondFailure(it) }
    }

    get(Router.BLOG_PATH) {
        val limit = call.request.queryParameters["limit"]
        val offset = call.request.queryParameters["offset"]
        val request = FetchBlogsRequest(limit?.let { limit.toInt() }, offset?.let { offset.toLong() })
        fetchBlogs(request)
            .onAsyncSuccess { respondSuccess(it) }
            .onAsyncFailure { respondFailure(it) }
    }
}
