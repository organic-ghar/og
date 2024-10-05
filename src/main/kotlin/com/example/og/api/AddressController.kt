package com.example.og.api

import com.example.og.entities.Address
import com.example.og.service.AddressService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/addresses")
class AddressController(
    private val addressService: AddressService,
    ) {

    @GetMapping
    fun getAllAddresses(): ResponseEntity<List<Address>> {
        val addresses = addressService.getAllAddresses()
        return ResponseEntity.ok(addresses)
    }

    @GetMapping("/{id}")
    fun getAddressById(@PathVariable id: UUID): ResponseEntity<Address?> {
        val address = addressService.getAddressById(id)
        return if (address != null) {
            ResponseEntity.ok(address)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/customer/{customerId}")
    fun getAddressesByCustomer(@PathVariable customerId: UUID): ResponseEntity<List<Address>> {
        val addresses = addressService.getAddressesByCustomer(customerId)
        return ResponseEntity.ok(addresses)
    }

    @PostMapping
    fun addAddress(@RequestBody address: Address): ResponseEntity<Address> {
        val createdAddress = addressService.addAddress(address)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress)
    }

    @PutMapping("/{id}")
    fun updateAddress(@PathVariable id: UUID, @RequestBody updatedAddress: Address): ResponseEntity<Address?> {
        val address = addressService.updateAddress(id, updatedAddress)
        return if (address != null) {
            ResponseEntity.ok(address)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAddress(@PathVariable id: UUID): ResponseEntity<Void> {
        addressService.deleteAddress(id)
        return ResponseEntity.noContent().build()
    }
}
