package com.example.bmiapp

import androidx.lifecycle.ViewModel

class BodyMassIndexViewModel : ViewModel() {
    var height: Float = 0f
    var weight: Float = 0f
    var bmi: Float = 0f

    fun calculateBMI() {
        if (height != 0f && weight != 0f) {
            bmi = weight / (height * height)
        }
    }
}

