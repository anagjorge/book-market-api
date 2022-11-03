package com.bookmarket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookmarketApplication

fun main(args: Array<String>) {
	runApplication<BookmarketApplication>(*args)
}
