package com.example.moviebackend.service


import com.example.moviebackend.config.FileSystemConfig
import com.example.moviebackend.dto.CommentDto
import com.example.moviebackend.util.findFile
import com.example.moviebackend.util.updateFile
import com.google.gson.Gson
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Service
class CommentService(
    private val fileSystemConfig: FileSystemConfig
) {
    private val gson = Gson()

    fun findCommentByVideoName(videoName: String): Flux<CommentDto> {
        val commentListJson = fileSystemConfig.fsMap.findFile(videoName)
        val commentDtoList = convertListJsonToDtoList(commentListJson)
        return commentDtoList.toFlux()
    }

    fun addCommentByVideoName(videoName: String, comment: CommentDto): Mono<CommentDto> {
        val commentListJson = fileSystemConfig.fsMap.findFile(videoName)
        val commentDtoList = convertListJsonToDtoList(commentListJson)
        val updatedComment = comment.changeId(commentDtoList.size)
        val addedCommentDtoList = commentDtoList.plus(updatedComment)
        writeToFileAndUpdateCache(videoName, addedCommentDtoList)
        return updatedComment.toMono()
    }

    fun updateComment(videoName: String, commentId: Int, comment: CommentDto): Mono<CommentDto> {
        val commentListJson = fileSystemConfig.fsMap.findFile(videoName)
        val sortedCommentDtoList = convertListJsonToDtoList(commentListJson)
        val updatedComment = comment.changeId(commentId)
        val updatedCommentDtoList = sortedCommentDtoList.map {
            if (it.id == commentId) {
                updatedComment
            } else it
        }
        writeToFileAndUpdateCache(videoName, updatedCommentDtoList)
        return updatedComment.toMono()
    }

    private fun convertListJsonToDtoList(commentListJson: String): List<CommentDto> =
        gson.fromJson(commentListJson, Array<CommentDto>::class.java).orEmpty().asList()

    private fun writeToFileAndUpdateCache(videoName: String, commentDtoList: List<CommentDto>): Unit {
        fileSystemConfig.fsMap.updateFile(videoName, commentDtoList)
        fileSystemConfig.refreshFileSystem()
    }
}
