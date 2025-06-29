package com.example.headuptest.parameters.domain

import com.example.headuptest.parameters.domain.entity.Parameters

interface ParametersRepository {
    fun saveParameters(parameters: Parameters)
    fun getParameters(): Parameters?
}