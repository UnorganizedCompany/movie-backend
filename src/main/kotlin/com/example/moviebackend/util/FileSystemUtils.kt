package com.example.moviebackend.util

import com.example.moviebackend.dto.CommentDto
import com.google.gson.Gson
import java.io.FileWriter
import java.io.PrintWriter

fun Map<String?, String>.findFile(videoName: String) =
    this.getOrDefault(videoName.addExtension(), "")

fun Map<String?, String>.updateFile(videoName: String, commentDtoList: List<CommentDto>) {
    val fileName = videoName.addExtension()
    val workingDir = System.getProperty("user.dir")

    try {
        PrintWriter(FileWriter("$workingDir/jsons/$fileName")).use {
            it.write(Gson().toJson(commentDtoList))
            it.close()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun String.addExtension(): String = "$this.json"
