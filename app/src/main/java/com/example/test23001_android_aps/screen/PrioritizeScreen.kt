package com.example.test23001_android_aps.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test23001_android_aps.domain.Diet
import com.example.test23001_android_aps.domain.HealthConcern
import com.example.test23001_android_aps.viewmodel.MainViewModel
import com.example.test23001_android_aps.widgets.ButtonWidget
import com.example.test23001_android_aps.widgets.HealthConcernWidget
import com.example.test23001_android_aps.widgets.PrioritizeItems
import com.google.android.material.chip.Chip
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrioritizeScreen(navController: NavController,mainViewModel: MainViewModel){

    var selectedChips by remember { mainViewModel.selectHealthConcern }
    val context = LocalContext.current

    val chipList =  mainViewModel.getHealthConcernList()
    Box (modifier = Modifier.fillMaxSize()){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Select the top health concerns * \n (up to 5)", modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        HealthConcernWidget(
                            list = chipList,
                            selectedList = selectedChips,
                            onOptionSelected = {
                                selectedChips = if (it in selectedChips) {
                                    selectedChips - it
                                } else {
                                    selectedChips + it
                                }
                            })

                        Text(
                            text = "Prioritize",
                            fontSize = MaterialTheme.typography.labelMedium.fontSize,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        PrioritizeItemList(selectedChips = selectedChips)

                    }

                    ButtonWidget(modifier= Modifier.fillMaxWidth()
                        .align(Alignment.BottomCenter)
                            ,onClickBack = {
                        navController.popBackStack()
                    }) {
                        if(mainViewModel.checkValidate()){
                            navController.navigate(Screen.Diet.route)
                        }else{
                            Toast.makeText(context,"Priority is less than 5",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
}

@Composable
fun PrioritizeItemList(selectedChips: List<HealthConcern>){
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items = selectedChips) {
            PrioritizeItems(healthConcern = it)
        }
    }
}




@Preview
@Composable
fun PrioritizePreview(){
    val viewModel = hiltViewModel<MainViewModel>()
    PrioritizeScreen(navController = rememberNavController(),viewModel)
}
