package com.shakib.samsungrndtask.domain.model

// Domain layer model that holds different states of the response
sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val throwable: Throwable) : ResponseState<Nothing>()
    data object Loading : ResponseState<Nothing>()
}
