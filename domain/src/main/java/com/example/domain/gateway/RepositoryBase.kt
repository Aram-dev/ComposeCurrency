package com.example.domain.gateway

import com.example.domain.connection.Connection
import com.example.domain.util.Constant.ERROR_CODE_DNS
import com.example.domain.util.Constant.ERROR_CODE_FORBIDDEN
import com.example.domain.util.Constant.ERROR_CODE_NETWORK_CONNECTION
import com.example.domain.util.Constant.ERROR_CODE_NOT_ALLOWED
import com.example.domain.util.Constant.ERROR_CODE_ROUTE
import com.example.domain.util.Constant.ERROR_CODE_SERVER
import com.example.domain.util.Constant.ERROR_CODE_UNAUTHORIZED
import com.example.domain.util.Constant.ERROR_CODE_UNDEFINED
import com.example.domain.util.Constant.ERROR_CODE_UNEXPECTED
import com.example.domain.util.Constant.ERROR_MESSAGE_NETWORK_CONNECTION
import com.example.domain.util.Constant.ERROR_MESSAGE_UNEXPECTED
import com.example.domain.util.ErrorCode
import com.example.domain.util.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class RepositoryBase {

    abstract val connectivity: Connection

    protected fun <T : Any> fetchData(
        apiDataProvider: suspend () -> Response<T>,
    ): Flow<ViewState<T>> = flow {

        if (connectivity.isNetworkAvailable()) {
            val data = apiDataProvider.invoke().handleResponse()
            emit(data)
        } else {
            val error = ErrorCode(ERROR_CODE_NETWORK_CONNECTION, ERROR_MESSAGE_NETWORK_CONNECTION)
            emit(ViewState.Failure(error))
        }
    }.catch {
        println(it)
        val error = ErrorCode(ERROR_CODE_UNEXPECTED, ERROR_MESSAGE_UNEXPECTED)
        emit(ViewState.Failure(error))
    }.flowOn(Dispatchers.IO)

    private fun <T : Any> Response<T>.handleResponse(): ViewState<T> {
        onSuccess {
            return ViewState.Success(it)
        }
        onFailure {
            return ViewState.Failure(it)
        }
        val error = ErrorCode(
            message = ERROR_MESSAGE_NETWORK_CONNECTION,
            code = ERROR_CODE_NETWORK_CONNECTION,
        )
        return ViewState.Failure(error)
    }

    private inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
        if (isSuccessful) {
            body().run { this?.let { b -> action.invoke(b) } }
        }
        return this
    }

    private inline fun <T : Any> Response<T>.onFailure(action: (ErrorCode) -> Unit) {
        if (!isSuccessful) errorBody()?.run {
            val code = code()
            val message = message()
            when (code()) {
                ERROR_CODE_UNAUTHORIZED -> logResponse("ERROR_UNAUTHORIZED::::::::: $code")
                ERROR_CODE_NOT_ALLOWED -> logResponse("ERROR_CODE_NOT_ALLOWED::::::::: ${string()}")
                ERROR_CODE_FORBIDDEN -> logResponse("ERROR_FORBIDDEN::::::::: $code")
                ERROR_CODE_ROUTE -> logResponse("ERROR_ROUTE::::::::: $code")
                ERROR_CODE_SERVER -> logResponse("ERROR_SERVER::::::::: $code")
                ERROR_CODE_DNS -> logResponse("ERROR_DNS::::::::: $code")
                ERROR_CODE_UNDEFINED -> logResponse("ERROR_UNDEFINED::::::::: $code")
                else -> logResponse("Code::::::::: ${string()}")
            }
            action(
                ErrorCode(
                    message = message,
                    code = code,
                )
            )
        }
    }

    private fun logResponse(s: String) = println(s)
}