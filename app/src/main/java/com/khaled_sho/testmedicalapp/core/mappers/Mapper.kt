package com.khaled_sho.testmedicalapp.core.mappers

interface Mapper<DTO : Any, MODEL : Any> {
    fun toModel(value: DTO): MODEL
    fun fromModel(value: MODEL): DTO
}