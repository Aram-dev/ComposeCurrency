package com.example.domain.resulthandler

import com.example.domain.util.ErrorCode
import kotlinx.coroutines.flow.Flow

sealed class ResultBase<out T : Any>
data class Success<out T : Any>(val data: T?) : ResultBase<T>()
data class Failure(val error: ErrorCode) : ResultBase<Nothing>()

suspend fun <T : Any> Flow<ResultBase<T>>.onCollect(
    onSuccess: (T?) -> Unit,
    onError: (ErrorCode?) -> Unit
) {
    collect {
        if (it is Success) onSuccess(it.data)
        if (it is Failure) onError(it.error)
    }
}

inline fun <T : Any> ResultBase<T>.onSuccess(action: (T?) -> Unit): ResultBase<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T : Any> ResultBase<T>.onFailure(action: (ErrorCode) -> Unit) {
    if (this is Failure) action(error)
}