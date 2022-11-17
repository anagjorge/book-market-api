package com.bookmarket.controller

import com.bookmarket.controller.request.PostBookRequest
import com.bookmarket.controller.request.PutBookRequest
import com.bookmarket.controller.response.BookResponse
import com.bookmarket.controller.response.PageResponse
import com.bookmarket.extension.toBookModel
import com.bookmarket.extension.toPageResponse
import com.bookmarket.extension.toResponse
import com.bookmarket.service.BookService
import com.bookmarket.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.findAll(pageable).map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("/actives")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.findActives(pageable).map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("{/id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
         bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest){
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

}