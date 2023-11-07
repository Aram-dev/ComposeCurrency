package com.example.domain.util

data class ErrorCode(
  val code: Int,
  val message: String?
) {
  override fun toString(): String {
    return "ErrorCode ===> (code=$code, message=$message)"
  }
}
