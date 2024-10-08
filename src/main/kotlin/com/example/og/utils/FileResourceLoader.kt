package com.example.og.utils

import com.opencsv.CSVReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.stereotype.Service
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

private typealias Path = String
private typealias Entries = List<List<String>>

@Service
class FileResourceLoader(@Qualifier("webApplicationContext") private val resourceLoader: ResourceLoader) {

    fun loadFiles(locationPattern: String): Array<out Resource> {

        return ResourcePatternUtils
            .getResourcePatternResolver(resourceLoader)
            .getResources("classpath*:$locationPattern")
    }

    fun readCsv(filename: String, dropHeader: Boolean = true): List<List<String>> {
        val dataResource = resourceLoader.getResource("classpath:/$filename")
        val reader = CSVReader(InputStreamReader(dataResource.inputStream, StandardCharsets.UTF_8))

        return reader.readAll().map { line ->
            line.map {
                it.trim()
            }
        }.apply {
            if (dropHeader) {
                drop(1) // partition into header & data
            }
        }
    }

    fun readCsvAndPath(filename: String, dropHeader: Boolean = true): List<Pair<Path, Entries>> {
        val resource =
            ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources("classpath*:$filename")

        return resource.map {
            Pair(
                it.url.path,
                CSVReader(InputStreamReader(it.inputStream, StandardCharsets.UTF_8)).readAll().map { line ->
                    line.map {
                        it.trim()
                    }
                }.apply {
                    if (dropHeader) {
                        drop(1) // partition into header & data
                    }
                }
            )
        }
    }
}

