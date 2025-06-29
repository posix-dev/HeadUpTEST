package com.example.headuptest.parameters.data

import com.example.headuptest.model.Parameters
import com.example.headuptest.parameters.shared.ParametersRepository
import javax.inject.Inject

class ParametersRepositoryImpl @Inject constructor(
    private val paramsHandler: ParametersHandler
): ParametersRepository {

    override fun saveParameters(parameters: Parameters) {
        paramsHandler.saveParams(parameters)
    }

    override fun getParameters(): Parameters? {
        return paramsHandler.getParams()
    }

}