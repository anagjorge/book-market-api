package com.bookmarket.service

import com.bookmarket.repository.CustomerRepository
import com.bookmarket.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
): UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
       val customer =  customerRepository.findById(id.toInt())
            .orElseThrow()
        return UserCustomDetails(customer)
    }
}