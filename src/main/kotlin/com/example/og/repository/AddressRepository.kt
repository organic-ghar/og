package com.example.og.repository

import com.example.og.entities.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressRepository : JpaRepository<Address, UUID> {
    fun findByCustomerId(customerId: UUID): List<Address>
    fun findByActive(active: Boolean): List<Address>
}

