package com.example.og.repository

import com.example.og.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByActive(active: Boolean): List<Customer>
}
