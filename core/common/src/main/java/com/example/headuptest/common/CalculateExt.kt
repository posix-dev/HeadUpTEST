package com.example.headuptest.common

import com.example.headuptest.model.Parameters

fun Parameters.calculateProtein(): Double {
    return ((0.2 * calculateCalories()) / 4)
}

fun Parameters.calculateFats(): Double {
    return ((0.25 * calculateCalories()) / 9)
}

fun Parameters.calculateCarbs(): Double {
    return ((0.55 * calculateCalories()) / 4)
}

fun Parameters.calculateCalories(): Double {
    val bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5
    val calories = bmr * 1.55
    return calories
}