package io.yap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YapApplication

fun main(args: Array<String>) {
	runApplication<YapApplication>(*args)
}
