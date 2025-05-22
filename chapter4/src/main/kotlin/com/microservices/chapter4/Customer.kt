package com.microservices.chapter4

data class Customer(var id: Int = 0, var name: String = "", val telephone: Telephone? = null) {
    data class Telephone(var countryCode: String = "", var telephoneNumber: String = "")
}
