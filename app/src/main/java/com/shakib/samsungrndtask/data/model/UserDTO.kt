package com.shakib.samsungrndtask.data.model

import com.shakib.samsungrndtask.domain.model.AddressModel
import com.shakib.samsungrndtask.domain.model.CompanyModel
import com.shakib.samsungrndtask.domain.model.GeoModel
import com.shakib.samsungrndtask.domain.model.UserModel

// Data layer models to parse API response
data class UserDTO(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDTO,
    val phone: String,
    val website: String,
    val company: CompanyDTO
)

data class AddressDTO(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDTO
)

data class GeoDTO(
    val lat: String,
    val lng: String
)

data class CompanyDTO(
    val name: String,
    val catchPhrase: String,
    val bs: String
)


// Use these functions to map data layer models to domain layer models
fun UserDTO.toDomainModel() = UserModel(
    id = id,
    name = name,
    username = username,
    email = email,
    address = address.toDomainModel(),
    phone = phone,
    website = website,
    company = company.toDomainModel()
)

fun AddressDTO.toDomainModel() = AddressModel(
    street = street,
    suite = suite,
    city = city,
    zipcode = zipcode,
    geo = geo.toDomainModel()
)

fun GeoDTO.toDomainModel() = GeoModel(
    lat = lat,
    lng = lng
)

fun CompanyDTO.toDomainModel() = CompanyModel(
    name = name,
    catchPhrase = catchPhrase,
    bs = bs
)
