package com.example.domain.util

object Constant {
    const val API_BASE_URL = "https://api.apilayer.com/exchangerates_data/"
    const val DATABASE_NAME = "compose-currency"
    const val DATABASE_VERSION = 1
    const val DATABASE_EXPORT_SCHEMA = false

    const val ERROR_CODE_UNEXPECTED = 601

    // All other error cases
    const val ERROR_CODE_DEVICE: Int = 400
    // Invalid Token (Refresh it)
    const val ERROR_CODE_UNAUTHORIZED = 401
    // The password or number is incorrect
    const val ERROR_CODE_FORBIDDEN: Int = 403
    // Not found
    const val ERROR_CODE_ROUTE: Int = 404
    // Method Not Allowed
    const val ERROR_CODE_NOT_ALLOWED: Int = 405
    // Error with server
    const val ERROR_CODE_SERVER: Int = 500
    // Error with dns
    const val ERROR_CODE_DNS: Int = 502
    // Error NULL data
    const val ERROR_CODE_UNDEFINED: Int = 511
    // Error No connection
    const val ERROR_CODE_NETWORK_CONNECTION = 455


    const val ERROR_MESSAGE_UNEXPECTED = "Something went wrong.Please try again"
    const val ERROR_MESSAGE_NETWORK_CONNECTION = "Please check your connection and try again."
}