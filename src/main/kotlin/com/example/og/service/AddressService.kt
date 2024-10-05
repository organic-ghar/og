package com.example.og.service

import com.example.og.entities.Address
import com.example.og.repository.AddressRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AddressService(
    private val addressRepository: AddressRepository,
) {

    fun getAllAddresses(): List<Address> {
        return addressRepository.findAll()
    }

    fun getAddressById(addressId: UUID): Address? {
        return addressRepository.findById(addressId).orElse(null)
    }

    fun getAddressesByCustomer(customerId: UUID): List<Address> {
        return addressRepository.findByCustomerId(customerId)
    }

    fun addAddress(address: Address): Address {
        return addressRepository.save(address)
    }

    fun updateAddress(addressId: UUID, updatedAddress: Address): Address? {
        val existingAddress = addressRepository.findById(addressId).orElse(null) ?: return null
        return addressRepository.save(updatedAddress.copy(id = existingAddress.id))
    }

    fun deleteAddress(addressId: UUID) {
        addressRepository.deleteById(addressId)
    }
}
