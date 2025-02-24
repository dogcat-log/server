package com.back.dogcatlog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DogcatlogApplication

fun main(args: Array<String>) {
	runApplication<DogcatlogApplication>(*args)
}
