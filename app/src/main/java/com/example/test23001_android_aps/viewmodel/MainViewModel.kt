package com.example.test23001_android_aps.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.test23001_android_aps.domain.Allergic
import com.example.test23001_android_aps.domain.Diet
import com.example.test23001_android_aps.domain.HealthConcern
import com.example.test23001_android_aps.domain.SubmitFormData
import com.example.test23001_android_aps.domain.SurveyQuestion
import com.example.test23001_android_aps.dummy.dummySurveyList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val context: Context
): ViewModel() {

    private var diets: List<Diet> = emptyList()
    private var healthConcernList: List<HealthConcern> = emptyList()
    private var allergicList: List<Allergic> = emptyList()

     val questionList = mutableStateOf( dummySurveyList.associate { it.id to it.answerOptions[0] })

    var selectedDiet =  mutableStateOf<List<Diet>>(emptyList())
    var selectHealthConcern =  mutableStateOf<List<HealthConcern>>(emptyList())
    var selectedAllergic = mutableStateOf<Set<Allergic>>(emptySet())

    private var _submitFormData = mutableStateOf(SubmitFormData())
    val submitFormData = _submitFormData

    init {
        // Load diets from the JSON file during initialization
        loadDietsFromJson("diets.json")
        loadDietsFromJson("healthconcern.json")
        loadDietsFromJson("allergies.json")
    }

    fun updateSubmitFormData(formData: SubmitFormData) {
        _submitFormData.value = formData
    }

    private fun readJsonFromAssets(fileName: String): String {
        val inputStream = context.assets.open(fileName)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?

        try {
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } finally {
            bufferedReader.close()
            inputStream.close()
        }

        return stringBuilder.toString()
    }

    private fun loadDietsFromJson(fileName: String) {
        try {
            val json: String = readJsonFromAssets(fileName)
            val jsonObject = JSONObject(json)
            val dietsArray = jsonObject.getJSONArray("data")
            when(fileName){
                "diets.json" -> {
                    val data = Gson().fromJson<List<Diet>?>(dietsArray.toString(), object : TypeToken<List<Diet>>() {}.type).toMutableList()
                    data.add(0,Diet(0,"none",""))
                    diets = data
                }
                "healthconcern.json" -> healthConcernList = Gson().fromJson(dietsArray.toString(), object : TypeToken<List<HealthConcern>>() {}.type)
                else -> allergicList = Gson().fromJson(dietsArray.toString(), object : TypeToken<List<Allergic>>() {}.type)
            }


        } catch (e: IOException) {
            // Handle the exception (e.g., log it or show an error message)
            e.printStackTrace()
        }
    }

    fun getDietList ()= diets
    fun getHealthConcernList ()= healthConcernList
    fun getAllergicList ()= allergicList

    fun getSubmitData() = submitFormData.value

    fun checkValidate() = selectHealthConcern.value.size >= 5 && selectHealthConcern.value.isNotEmpty()
    fun checkDietValidate() = selectedDiet.value.filter { it.id != 0 }.isNotEmpty()
    fun checkAllergicValidate() = selectedAllergic.value.isNotEmpty()

}