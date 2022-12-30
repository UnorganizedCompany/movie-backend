package com.example.moviebackend.config

import org.springframework.context.annotation.Configuration
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.exists

@Configuration
class FileSystemConfig {
    init {
        initializeWorkingDir()
        refreshFileSystem()
    }

    lateinit var fsMap: Map<String?, String>
    lateinit var currentWorkingDir: String


    fun refreshFileSystem() {
        val dir = File(getStorageDir())
        val fileList = dir.listFiles()
        val result = fileList
            .associateBy { it.name }
            .mapValues { it.value.readText() }
        this.fsMap = result
    }

    fun initializeWorkingDir() {
        currentWorkingDir = File("").absolutePath
        val storageUrl = "$currentWorkingDir/jsons"
        if (!Paths.get(storageUrl).exists()) {
            Files.createDirectory(Paths.get(storageUrl))
        }
    }

    fun getStorageDir(): String = "$currentWorkingDir/jsons"
}
