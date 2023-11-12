package com.example.test23001_android_aps.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsProperties.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.test23001_android_aps.domain.Diet
import com.example.test23001_android_aps.viewmodel.MainViewModel
import com.example.test23001_android_aps.widgets.ButtonWidget
import com.example.test23001_android_aps.widgets.CheckWithLabel
import com.example.test23001_android_aps.widgets.InfoPopover


@Composable
fun DietScreen(navController: NavController,mainViewModel: MainViewModel){

    var selectedDiet by remember { mainViewModel.selectedDiet }
    var infoPopoverState by remember { mutableStateOf(false) }

    val dataList = mainViewModel.getDietList()
    val context = LocalContext.current

    Column {
        Text(
            text = "Select the diet you follow. *", modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        // Show Info Popover if needed

        LazyColumn {
            items(dataList) { diet ->
                CheckWithLabel(
                    diet = diet,
                    isCheck = selectedDiet.contains(diet),
                    onCheckChange = { d ->
                        selectedDiet = if (selectedDiet.contains(d)) {
                            selectedDiet - d
                        }else selectedDiet + d
                    },
                    onClickInfo = {
                        infoPopoverState = true
                    }
                )

                if (infoPopoverState) {
                    InfoPopover(diet = diet.tool_tip ) {
                        infoPopoverState = false
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWidget(
            modifier = Modifier.fillMaxWidth(),
            onClickBack = {
            navController.popBackStack()
        }) {
            if(mainViewModel.checkDietValidate()){
                navController.navigate(Screen.Allergic.route)
            }else{
                Toast.makeText(context,"Select Something",Toast.LENGTH_SHORT).show()
            }
        }
    }
}