package com.weatherapi.core.view_state

sealed class ViewState<out R> {

    private var identifier: String? = null

    data class Success<out T>(
        val data: T
    ) : ViewState<T>()

    object Empty : ViewState<Nothing>()


    data class Error<out T>(
        val errorData: T? = null,
        val error: Throwable? = null,
        val errorMessage: String? = null
    ) : ViewState<T>()

    object Loading : ViewState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Empty -> "Empty"
            is Error<*> -> "Error[error=$error]"
            is Loading -> "Loading"
        }
    }

    fun isSuccess() = this is Success<*>

    fun isEmpty() = this == Empty

    fun isError() = this is Error<*>

    fun isLoading() = this is Loading

    fun withIdentifier(identifier: String): ViewState<R> {
        this.identifier = identifier
        return this
    }
}