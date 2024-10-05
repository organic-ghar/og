package com.example.og.api

import com.example.og.entities.Lookup
import com.example.og.service.LookupService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lookups")
class LookupController(private val lookupService: LookupService) {

    @GetMapping
    fun getAllLookups(): ResponseEntity<List<Lookup>> {
        val lookups = lookupService.getAllLookups()
        return ResponseEntity.ok(lookups)
    }

    @GetMapping("/name/{name}")
    fun getLookupsByName(@PathVariable name: String): ResponseEntity<List<Lookup>> {
        val lookups = lookupService.getLookupsByName(name)
        return ResponseEntity.ok(lookups)
    }

    @PostMapping
    fun addLookup(@RequestBody lookup: Lookup): ResponseEntity<Lookup> {
        val createdLookup = lookupService.addLookup(lookup)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLookup)
    }

    @PutMapping("/{id}")
    fun updateLookup(@PathVariable id: Long, @RequestBody updatedLookup: Lookup): ResponseEntity<Lookup?> {
        val lookup = lookupService.updateLookup(id, updatedLookup)
        return if (lookup != null) {
            ResponseEntity.ok(lookup)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteLookup(@PathVariable id: Long): ResponseEntity<Void> {
        lookupService.deleteLookup(id)
        return ResponseEntity.noContent().build()
    }
}
