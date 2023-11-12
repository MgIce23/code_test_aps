package com.example.test23001_android_aps.domain

data class SubmitFormData(
    val health_concerns: List<HealthConcern>? = emptyList(),
    val diets: List<Diet>? = emptyList(),
    val allergies: Set<Allergic>? = emptySet(),
    var is_daily_exposure : Boolean = false,
    var is_somke : Boolean = false,
    var alchol: String = ""
)
