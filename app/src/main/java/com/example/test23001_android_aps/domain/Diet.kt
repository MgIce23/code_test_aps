package com.example.test23001_android_aps.domain

data class Diet(
    val id: Int,
    val name: String,
    val tool_tip: String,
    var isCheck : Boolean = false
)