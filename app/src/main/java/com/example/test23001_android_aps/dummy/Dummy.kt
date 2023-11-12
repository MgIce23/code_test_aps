package com.example.test23001_android_aps.dummy

import com.example.test23001_android_aps.domain.SurveyQuestion

val dummySurveyList  = listOf(
    SurveyQuestion(
        id = 1,
        question = " Is your daily exposure  to sun is limited *",
        answerOptions = listOf("Yes", "No")
    ),
    SurveyQuestion(
        id = 2,
        question = "Do you current smoke ( tobacco or marijuana ? ) *",
        answerOptions = listOf("Yes","No")
    ),
    SurveyQuestion(
            id = 3,
    question = "On average, how many alcoholic beverages do you have in a week?  *",
    answerOptions = listOf("0-5","2-5","5 +")
)
    // Add more survey questions as needed
)