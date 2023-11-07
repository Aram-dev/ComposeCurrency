package com.example.domain.mapper

interface DbMapper<T : Any?> {
    fun mapToDbEntity(): T
}