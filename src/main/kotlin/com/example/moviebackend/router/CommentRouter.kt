package com.example.moviebackend.router

import com.example.moviebackend.handler.CommentServiceHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class CommentRouter(
    private val serviceHandler: CommentServiceHandler
) {
    @Bean
    fun commentRouterFunction() = router {
        "/videos".nest {
            GET("/{videoName}/comments", serviceHandler::getAllComment)
            POST("/{videoName}/comments", serviceHandler::addComment)
            PUT("/{videoName}/comments/{commentId}", serviceHandler::updateComment)
        }
    }
}
