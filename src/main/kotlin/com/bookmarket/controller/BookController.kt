package com.bookmarket.controller

import com.bookmarket.controller.request.PostBookRequest
import com.bookmarket.controller.request.PutBookRequest
import com.bookmarket.extension.toBookModel
import com.bookmarket.model.BookModel
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.awt.print.Book

@RestController
@RequestMapping("books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun getAll(): List<BookModel> {
        return bookService.getAll()
    }


    @GetMapping("{/id}")
    fun getById(@PathVariable id: Int): BookModel {
        return bookService.getById(id)
    }

    @GetMapping("/actives")
    fun getActives(): List<BookModel> {
        return bookService.getActives()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        return bookService.deleteById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest){
        val bookSaved = bookService.getById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

}