package com.example.og.repository

import com.example.og.entities.Lookup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LookupRepository : JpaRepository<Lookup, UUID> {
    fun findByName(name: String): List<Lookup>
}

