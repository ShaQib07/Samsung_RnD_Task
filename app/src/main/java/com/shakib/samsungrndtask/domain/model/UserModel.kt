package com.shakib.samsungrndtask.domain.model

// Domain layer model to keep the domain layer separate from other layers
data class UserModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressModel,
    val phone: String,
    val website: String,
    val company: CompanyModel
)

// Domain layer model to keep the domain layer separate from other layers
data class AddressModel(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoModel
)

// Domain layer model to keep the domain layer separate from other layers
data class GeoModel(
    val lat: String,
    val lng: String
)

// Domain layer model to keep the domain layer separate from other layers
data class CompanyModel(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
