package com.bookmarket.service

import com.bookmarket.enums.BookStatus
import com.bookmarket.enums.Errors
import com.bookmarket.exceptions.NotFoundException
import com.bookmarket.model.BookModel
import com.bookmarket.model.CustomerModel
import com.bookmarket.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.Pageable

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{NotFoundException(Errors.BM101.message.format(id), Errors.BM101.code)}
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for(book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

}
