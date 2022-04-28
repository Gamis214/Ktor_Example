package com.miclaro.ktor_example.Services.KtorApiServices

import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

object KtorClient {

    val httpClient = HttpClient {

        install(JsonFeature){
            this.serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                this.encodeDefaults = true
                this.ignoreUnknownKeys = true
                this.prettyPrint = true
                this.isLenient = true
            })
        }

        install(Logging){
            this.logger = object : Logger{
                override fun log(message: String) {
                    Log.d("SERVICES MESSAGE","log: $message")
                }
            }
            this.level = LogLevel.ALL
        }

        install(HttpTimeout){
            this.connectTimeoutMillis = 30_000
            this.requestTimeoutMillis = 30_000
            this.socketTimeoutMillis = 30_000
        }

        install(DefaultRequest){
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        /*this.defaultRequest {
            this.contentType(ContentType.Application.Json)
            this.accept(ContentType.Application.Json)
        }*/
    }

}