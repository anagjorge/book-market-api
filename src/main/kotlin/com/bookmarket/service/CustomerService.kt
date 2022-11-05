package com.bookmarket.service

import com.bookmarket.model.CustomerModel
import com.bookmarket.request.PostCustomerRequest
import com.bookmarket.request.PutCustomerRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*


@Service
class CustomerService {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let{
            return customers.filter { it.name.contains(name, true) }
        }

        return customers
    }

    fun getCustomer(id: String): CustomerModel{
        return customers.filter{it.id == id}.first()
    }

    fun create(customer: CustomerModel){
        val id = if(customers.isEmpty()){
            1
        } else {
            customers.last().id!!.toInt() + 1
        }.toString()

        customer.id = id
        customers.add(customer)
    }


    fun update(customer:CustomerModel) {
        customers.filter{it.id == customer.id}.first().let {
            it.name = customer.name
            it.email= customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf{it.id == id}
    }
}