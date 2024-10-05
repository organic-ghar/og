package com.example.og.repository

import com.example.og.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<Customer, UUID> {
    fun findByActive(active: Boolean): List<Customer>
}
