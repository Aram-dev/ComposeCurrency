package com.example.domain.mapper

interface DomainMapper<T : Any?> {
    fun mapToDomainModel(): T
}