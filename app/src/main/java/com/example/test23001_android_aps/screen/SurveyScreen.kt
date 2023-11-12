package com.example.test23001_android_aps.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.test23001_android_aps.R
import com.example.test23001_android_aps.domain.SurveyQuestion
import com.example.test23001_android_aps.dummy.dummySurveyList
import com.example.test23001_android_aps.viewmodel.MainViewModel
import com.google.gson.Gson


@Composable
fun SurveyScreen(navController: NavController, mainViewModel: MainViewModel) {
    var selectedAnswers by remember { mainViewModel.questionList }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(dummySurveyList) { question ->
                SurveyQuestionCard(
                    question = question,
                    selectedAnswer = selectedAnswers[question.id],
                    onAnswerSelected = { answer ->
                        selectedAnswers = selectedAnswers + (question.id to answer)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add a button to submit the survey with selected answers
        Button(
            onClick = {
                val updatedAnswers = selectedAnswers.mapValues { (_, answer) ->
                    when (answer) {
                        "Yes" -> true
                        "No" -> false
                        else -> answer
                    }
                }
                // Process the selected answers
                updatedAnswers.forEach { (questionId, answer) ->
                    println(" what $questionId $answer")
                }
                val submitFormData = mainViewModel.submitFormData.value.copy(
                    health_concerns = mainViewModel.selectHealthConcern.value,
                    diets  = mainViewModel.selectedDiet.value,
                    allergies = mainViewModel.selectedAllergic.value,
                    is_daily_exposure = updatedAnswers[1]?.equals(true) ?: false,
                    is_somke = updatedAnswers[2]?.equals(true) ?: false,
                    alchol = updatedAnswers[3].toString()
                )
                println("haha ${Gson().toJson(submitFormData)}")
                //   submitFormData?.let { viewModel.updateSubmitFormData(it) }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.orange), // Change this color to your desired button color
                contentColor =  colorResource(id = R.color.black) // Change this color to the desired text color
            )
        ) {
            Text(text = "Get My Personalized Vitamin", color = Color.White)
        }
    }


}


@Composable
fun SurveyQuestionCard(
    question: SurveyQuestion,
    selectedAnswer: Any?,
    onAnswerSelected: (Any) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(text = question.question, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(16.dp))

            // Display radio buttons for answer options
            question.answerOptions.forEach { answer ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = answer == selectedAnswer,
                        onClick = { onAnswerSelected(answer) },
                        modifier = Modifier
                            .padding(4.dp)
                    )
                    Text(text = answer.toString(), modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}
