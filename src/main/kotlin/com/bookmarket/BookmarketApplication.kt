package com.bookmarket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class BookmarketApplication

fun main(args: Array<String>) {
	runApplication<BookmarketApplication>(*args)
}


