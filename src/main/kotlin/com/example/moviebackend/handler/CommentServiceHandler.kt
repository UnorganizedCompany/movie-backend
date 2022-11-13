package com.example.moviebackend.handler

import com.example.moviebackend.dto.CommentDto
import com.example.moviebackend.service.CommentService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class CommentServiceHandler(
    private val commentService: CommentService
) {
    fun getAllComment(request: ServerRequest): Mono<ServerResponse> {
        val videoName = request.pathVariable("videoName")
        val result = commentService.findCommentByVideoName(videoName)
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result)
    }

    fun addComment(request: ServerRequest): Mono<ServerResponse> {
        val videoName = request.pathVariable("videoName")
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
            request.bodyToMono<CommentDto>().flatMap { commentService.addCommentByVideoName(videoName, it) }
        )
    }

    fun updateComment(request: ServerRequest): Mono<ServerResponse> {
        val videoName = request.pathVariable("videoName")
        val commentId = request.pathVariable("commentId").toInt()
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
            request.bodyToMono<CommentDto>().flatMap { commentService.updateComment(videoName, commentId, it) }
        )
    }
}
