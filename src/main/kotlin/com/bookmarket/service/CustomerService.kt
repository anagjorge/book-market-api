package com.bookmarket.service

import com.bookmarket.enums.CustomerStatus
import com.bookmarket.enums.Errors
import com.bookmarket.exceptions.NotFoundException
import com.bookmarket.model.CustomerModel
import com.bookmarket.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {
    val customers = mutableListOf<CustomerModel>()

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findAll(name: String?, pageable: Pageable): Page<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll(pageable)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.BM201.message.format(id), Errors.BM201.code) }
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
       return !customerRepository.existsByEmail(email)

    }
}