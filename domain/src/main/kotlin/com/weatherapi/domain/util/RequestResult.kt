package com.weatherapi.domain.util

sealed class RequestResult<out T> {
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(
        val throwable: Throwable?,
        val errorCode: Int? = null
    ) :
        RequestResult<Nothing>() {
        companion object {
            fun defaultRequestError() = Error(Exception("Unexpected result"))
        }
    }
}

val RequestResult<*>?.succeeded
    get() = this != null && this is RequestResult.Success && data != null

val <T> RequestResult<T>.data
    get() = if (this.succeeded) (this as RequestResult.Success<T>).data else null

val <T> RequestResult<T>.error
    get() = when (this) {
        is RequestResult.Error -> this.throwable
        else -> null
    }

val <T> RequestResult<T>.errorCode
    get() = when (this) {
        is RequestResult.Error -> this.errorCode
        else -> null
    }

val <T> RequestResult<T>.errorMsg
    get() = when (this) {
        is RequestResult.Error -> error?.message.orEmpty()
        else -> null
    }
