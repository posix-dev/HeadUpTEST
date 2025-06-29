package com.example.headuptest.parameters.data

import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject
import androidx.core.content.edit
import com.example.headuptest.model.Parameters
import dagger.hilt.android.qualifiers.ApplicationContext

class ParametersHandler @Inject constructor(
    private val gson: Gson,
    @ApplicationContext
    private val context: Context,
) {
    fun saveParams(params: Parameters) {
        val sharedPref = context.getSharedPreferences(SHAR_PREFS_KEY, Context.MODE_PRIVATE)

        sharedPref.edit {
            val jsonString = gson.toJson(params)
            putString(PARAMS_KEY, jsonString)
        }
    }

    fun getParams(): Parameters? {
        val sharedPref = context.getSharedPreferences(SHAR_PREFS_KEY, Context.MODE_PRIVATE)
        val jsonString = sharedPref.getString(PARAMS_KEY, null)
        return Gson().fromJson(jsonString, Parameters::class.java)
    }

    private companion object {
        const val PARAMS_KEY = "user_key"
        const val SHAR_PREFS_KEY = "MyPrefs"
    }
}