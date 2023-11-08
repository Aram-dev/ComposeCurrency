package com.example.features.common.filter

import com.example.domain.entity.Rate

fun List<Rate>.AtoZ(): List<Rate> = this.sortedBy { it.code }
fun List<Rate>.ZtoA(): List<Rate> = this.sortedByDescending { it.code }
fun List<Rate>.asc(): List<Rate> = this.sortedBy { it.value }
fun List<Rate>.desc(): List<Rate> = this.sortedByDescending { it.value }

fun List<Rate>.filter(key: String?): List<Rate> {
    return when (key) {
        "az" -> AtoZ()
        "za" -> ZtoA()
        "asc" -> asc()
        "desc" -> desc()
        else -> AtoZ()
    }
}