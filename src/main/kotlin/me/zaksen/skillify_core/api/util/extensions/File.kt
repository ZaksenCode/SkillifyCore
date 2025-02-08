package me.zaksen.skillify_core.api.util.extensions

import java.io.File

fun File.loadDirectoryFilesNames(): MutableSet<String> {
    if(!this.exists()) {
        this.mkdirs()
    }

    val result = mutableSetOf<String>()
    val files = this.listFiles() ?: return mutableSetOf()

    for(file in files) {
        if(file.isDirectory) {
            result.addAll(file.loadDirectoryFilesNames())
        }

        result.add(file.name)
    }

    return result
}

fun File.loadDirectoryFiles(): MutableSet<File> {
    if(!this.exists()) {
        this.mkdirs()
    }

    val result = mutableSetOf<File>()
    val files = this.listFiles() ?: return mutableSetOf()

    for(file in files) {
        if(file.isDirectory) {
            result.addAll(file.loadDirectoryFiles())
        }

        result.add(file)
    }

    return result
}