package com.example.headuptest.parameters.shared

import com.example.headuptest.model.Parameters

interface ParametersRepository {
    fun saveParameters(parameters: Parameters)
    fun getParameters(): Parameters?
}