package com.example.og.service

import com.example.og.entities.Lookup
import com.example.og.repository.LookupRepository
import org.springframework.stereotype.Service

@Service
class LookupService(
    private val lookupRepository: LookupRepository,
) {

    fun getAllLookups(): List<Lookup> {
        return lookupRepository.findAll()
    }

    fun getLookupsByName(name: String): List<Lookup> {
        return lookupRepository.findByName(name)
    }

    fun addLookup(lookup: Lookup): Lookup {
        return lookupRepository.save(lookup)
    }

    fun updateLookup(lookupId: Long, updatedLookup: Lookup): Lookup? {
        val existingLookup = lookupRepository.findById(lookupId).orElse(null) ?: return null
        return lookupRepository.save(updatedLookup.copy(id = existingLookup.id))
    }

    fun deleteLookup(lookupId: Long) {
        lookupRepository.deleteById(lookupId)
    }
}
