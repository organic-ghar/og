package com.example.og.repository

import com.example.og.entities.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {
    fun findByCustomerId(customerId: Long): List<Address>
    fun findByActive(active: Boolean): List<Address>
}

