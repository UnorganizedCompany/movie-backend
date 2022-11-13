package com.example.moviebackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class FileSystemConfig {
    init {
        refreshFileSystem()
    }

    lateinit var fsMap: Map<String?, String>

    @Bean(name = ["json-directory"])
    fun fileSystem(): Map<String?, String> = this.fsMap


    fun refreshFileSystem() {
        val workingDir = System.getProperty("user.dir")
        val dir = File("$workingDir/jsons")
        val fileList = dir.listFiles()

        val result = fileList
            .associateBy { it.name }
            .mapValues { it.value.readText() }
        this.fsMap = result
    }
}
