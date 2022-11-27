package com.example.moviebackend.config

import com.example.moviebackend.util.getWorkingDir
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class FileSystemConfig {
    init {
        refreshFileSystem()
    }

    lateinit var fsMap: Map<String?, String>

    fun refreshFileSystem() {
        val dir = File(getWorkingDir())
        val fileList = dir.listFiles()
        val result = fileList
            .associateBy { it.name }
            .mapValues { it.value.readText() }
        this.fsMap = result
    }
}
