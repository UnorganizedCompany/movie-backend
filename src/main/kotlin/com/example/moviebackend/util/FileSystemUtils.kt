package com.example.moviebackend.util

import com.example.moviebackend.config.FileSystemConfig
import com.example.moviebackend.dto.CommentDto
import com.google.gson.Gson
import org.springframework.util.ResourceUtils
import java.io.FileWriter
import java.io.PrintWriter

fun Map<String?, String>.findFile(videoName: String) =
    this.getOrDefault(videoName.addExtension(), "")

fun Map<String?, String>.updateFile(videoName: String, commentDtoList: List<CommentDto>) {
    val fileName = videoName.addExtension()
    val workingDir = getWorkingDir()

    try {
        PrintWriter(FileWriter("$workingDir/$fileName")).use {
            it.write(Gson().toJson(commentDtoList))
            it.close()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getWorkingDir(): String {
    val localUrl = FileSystemConfig::class.java.classLoader.getResource("jsons")
    return if (!ResourceUtils.isJarURL(localUrl)) localUrl.path else "/app/jsons"
}

fun String.addExtension(): String = "$this.json"
