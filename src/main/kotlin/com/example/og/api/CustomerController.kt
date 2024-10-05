package com.example.og.api

import com.example.og.entities.Customer
import com.example.og.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    fun getAllCustomers(): ResponseEntity<List<Customer>> {
        val customers = customerService.getAllCustomers()
        return ResponseEntity.ok(customers)
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id: Long): ResponseEntity<Customer?> {
        val customer = customerService.getCustomerById(id)
        return if (customer != null) {
            ResponseEntity.ok(customer)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun addCustomer(@RequestBody customer: Customer): ResponseEntity<Customer> {
        val createdCustomer = customerService.addCustomer(customer)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer)
    }

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody updatedCustomer: Customer): ResponseEntity<Customer?> {
        val customer = customerService.updateCustomer(id, updatedCustomer)
        return if (customer != null) {
            ResponseEntity.ok(customer)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long): ResponseEntity<Void> {
        customerService.deleteCustomer(id)
        return ResponseEntity.noContent().build()
    }
}
