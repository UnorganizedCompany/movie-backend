package com.example.moviebackend.dto

data class CommentDto(
    val id: Int?,
    val author: String,
    val startTime: Long?,
    val endTime: Long?,
    val comment: String?,
    val resolved: Boolean
) {
    fun changeId(id: Int) = this.copy(id = id)
    fun changeResolved(resolved: Boolean) = this.copy(resolved = resolved)

}
