package org.example.project.data.remote.util

import co.touchlab.kermit.Logger
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@Serializable
data class LogWrapper<T>(val httpResponse: T)

sealed class ApiResponseWrapper<out T> {
    data class Success<T>(val data: T) : ApiResponseWrapper<T>()
    data class Failure(val message: String, val errorCode: String? = null) : ApiResponseWrapper<Nothing>()
    data class NetworkError(val reason: String) : ApiResponseWrapper<Nothing>()
    data class UnknownError(val error: String) : ApiResponseWrapper<Nothing>()
}

suspend inline fun <reified T> handleApiCall(call: suspend () -> T): ApiResponseWrapper<T> {
    return try {
        val result = call()
        logApiResponse(result = result, primaryTag = "Omi", secondaryTag = "Http")
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

inline fun <reified T> logApiResponse(
    result: T,
    primaryTag: String,
    secondaryTag: String
) {
    val json = Json { prettyPrint = true }
    val serializer = serializer<T>()
    val logOutput = json.encodeToString(LogWrapper.serializer(serializer), LogWrapper(result))
    Logger.withTag(primaryTag).d("$secondaryTag: \n$logOutput")
}
