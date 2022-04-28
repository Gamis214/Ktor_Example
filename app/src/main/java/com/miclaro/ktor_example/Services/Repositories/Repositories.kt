package com.miclaro.ktor_example.Services.Repositories

import com.miclaro.ktor_example.Services.DataModel.Response
import com.miclaro.ktor_example.Services.KtorApiServices.KtorClient
import io.ktor.client.request.*

object Repository{

    suspend fun getUserDataFromServices(): Response{
        return KtorClient.httpClient.use {
            it.get("https://dummy.restapiexample.com/api/v1/employees")
        }
    }

}