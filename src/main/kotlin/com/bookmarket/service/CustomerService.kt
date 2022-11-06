package com.bookmarket.service

import com.bookmarket.enums.CustomerStatus
import com.bookmarket.model.CustomerModel
import com.bookmarket.repository.CustomerRepository
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

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun getById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = getById(id)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}