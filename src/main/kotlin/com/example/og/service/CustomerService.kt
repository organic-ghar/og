package com.example.og.service

import com.example.og.entities.Customer
import com.example.og.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) {

    fun getAllCustomers(): List<Customer> {
        return customerRepository.findAll()
    }

    fun getCustomerById(customerId: Long): Customer? {
        return customerRepository.findById(customerId).orElse(null)
    }

    fun addCustomer(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    fun updateCustomer(customerId: Long, updatedCustomer: Customer): Customer? {
        val existingCustomer = customerRepository.findById(customerId).orElse(null) ?: return null
        return customerRepository.save(updatedCustomer.copy(id = existingCustomer.id))
    }

    fun deleteCustomer(customerId: Long) {
        customerRepository.deleteById(customerId)
    }
}
