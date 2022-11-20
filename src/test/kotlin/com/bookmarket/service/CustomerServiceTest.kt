package com.bookmarket.service

import com.bookmarket.enums.CustomerStatus
import com.bookmarket.enums.Role
import com.bookmarket.model.CustomerModel
import com.bookmarket.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.UUID

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder

    @InjectMockKs
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers when name is informed`() {
        val name = UUID.randomUUID().toString()
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

         every { customerRepository.findByNameContaining(name) } returns fakeCustomers

        val customers = customerService.getAll(name, Pageable.unpaged())

        assertEquals(fakeCustomers, customers)
        verify(exactly = 1) {customerRepository.findAll() }
        verify(exactly = 0) {customerRepository.findByNameContaining(any())}
    }


    fun buildCustomer(
        id: Int? = null,
        name: String = "customer name",
        email: String = "${UUID.randomUUID()}@email.com",
        password: String = "password"
    ) = CustomerModel(
        id = id,
        name = name,
        email= email,
        status = CustomerStatus.ATIVO,
        password = password,
        roles = setOf(Role.CUSTOMER)
    )

}