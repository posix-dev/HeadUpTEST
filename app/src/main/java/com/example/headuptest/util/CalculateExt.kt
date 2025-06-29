package com.example.headuptest.util

import com.example.headuptest.parameters.domain.entity.Parameters

fun Parameters.calculateProtein(): Double {
    return ((0.2 * getCalories()) / 4)
}

fun Parameters.calculateFats(): Double {
    return ((0.25 * getCalories()) / 9)
}

fun Parameters.calculateCarbs(): Double {
    return ((0.55 * getCalories()) / 4)
}

fun Parameters.getCalories(): Double {
    val bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5
    val calories = bmr * 1.55
    return calories
}