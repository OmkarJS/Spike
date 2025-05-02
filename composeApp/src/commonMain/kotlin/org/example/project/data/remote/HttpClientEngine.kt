package org.example.project.data.remote

import io.ktor.client.HttpClient

expect class HttpClientEngine() {
    fun create(): HttpClient
}