package com.miclaro.ktor_example.Services.DataModel

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val data: List<Data>,
    val message: String,
    val status: String
)

@Serializable
data class Data(
    val id: Int,
    val employee_name: String,
    val employee_salary: Int,
    val employee_age: Int,
    val profile_image: String
)