package com.example.og.service

import com.example.og.entities.Address
import com.example.og.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService(
    private val addressRepository: AddressRepository,
) {

    fun getAllAddresses(): List<Address> {
        return addressRepository.findAll()
    }

    fun getAddressById(addressId: Long): Address? {
        return addressRepository.findById(addressId).orElse(null)
    }

    fun getAddressesByCustomer(customerId: Long): List<Address> {
        return addressRepository.findByCustomerId(customerId)
    }

    fun addAddress(address: Address): Address {
        return addressRepository.save(address)
    }

    fun updateAddress(addressId: Long, updatedAddress: Address): Address? {
        val existingAddress = addressRepository.findById(addressId).orElse(null) ?: return null
        return addressRepository.save(updatedAddress.copy(id = existingAddress.id))
    }

    fun deleteAddress(addressId: Long) {
        addressRepository.deleteById(addressId)
    }
}
