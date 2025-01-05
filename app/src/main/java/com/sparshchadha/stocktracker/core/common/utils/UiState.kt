package com.sparshchadha.stocktracker.core.common.utils

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val error: String) : UiState<Nothing>
    data object Loading : UiState<Nothing>

    data object Empty : UiState<Nothing>
    data class LoadingWithData<T>(val data: T) : UiState<T>
}

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(
        val code: Int? = null,
        val message: String? = null,
        val exception: Throwable? = null
    ) : ApiResult<Nothing>
    data object Loading : ApiResult<Nothing>
}

fun <T> ApiResult<T>.toUiState(): UiState<T> = when (this) {
    is ApiResult.Success -> UiState.Success(data)
    is ApiResult.Error -> UiState.Error(message ?: "Unknown error")
    ApiResult.Loading -> UiState.Loading
}