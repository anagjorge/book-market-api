package com.bookmarket.controller

import com.bookmarket.extension.toCustomerModel
import com.bookmarket.controller.request.PostCustomerRequest
import com.bookmarket.controller.request.PutCustomerRequest
import com.bookmarket.controller.response.CustomerResponse
import com.bookmarket.controller.response.PageResponse
import com.bookmarket.extension.toPageResponse
import com.bookmarket.extension.toResponse
import com.bookmarket.security.OnlyAdminAcesssAllCustomers
import com.bookmarket.security.UserCanOnlyAcesssTheirOwnResource
import com.bookmarket.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping
    @OnlyAdminAcesssAllCustomers
    fun findAll(@RequestParam name: String?, @PageableDefault(page = 0, size = 10)pageable: Pageable)
    : PageResponse<CustomerResponse> {
        return customerService.findAll(name, pageable).map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("/{id}")
    @UserCanOnlyAcesssTheirOwnResource
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }

}