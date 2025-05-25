package org.example.project.data.remote.util

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

sealed class ApiResponseWrapper<out T> {
    data class Success<T>(val data: T) : ApiResponseWrapper<T>()
    data class Failure(val message: String, val errorCode: String? = null) : ApiResponseWrapper<Nothing>()
    data class NetworkError(val reason: String) : ApiResponseWrapper<Nothing>()
    data class UnknownError(val error: String) : ApiResponseWrapper<Nothing>()
}

suspend fun <T> handleApiCall(call: suspend () -> T): ApiResponseWrapper<T> {
    return try {
        val result = call()
        ApiResponseWrapper.Success(result)
    } catch (e: ClientRequestException) {
        ApiResponseWrapper.Failure("Client error: ${e.response.status}", e.response.status.value.toString())
    } catch (e: ServerResponseException) {
        ApiResponseWrapper.Failure("Server error: ${e.response.status}", e.response.status.value.toString())
    } catch (e: RedirectResponseException) {
        ApiResponseWrapper.Failure("Redirect error: ${e.response.status}", e.response.status.value.toString())
    } catch (e: Exception) {
        ApiResponseWrapper.UnknownError(e.message ?: "Unknown error")
    }
}
