package com.example.domain.util

sealed class ViewState<out R> {
    data object Idle : ViewState<Nothing>()
    data object Loading : ViewState<Nothing>()
    data class Success<out T>(val data: T) : ViewState<T>()
    data class Failure(val errorCode: ErrorCode?) : ViewState<Nothing>()
}