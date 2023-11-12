package com.example.test23001_android_aps.widgets

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test23001_android_aps.domain.HealthConcern

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HealthConcernWidget(
    list: List<HealthConcern>,
    selectedList : List<HealthConcern>,
    onOptionSelected: (HealthConcern) ->Unit
){
    FlowRow(modifier = Modifier.verticalScroll(ScrollState(0)), maxItemsInEachRow = 3) {
            list.forEach {
                FilterChip(
                    modifier = Modifier.padding(5.dp),
                    selected = it in selectedList,
                    onClick = { onOptionSelected(it) },
                    label = { Text(text = it.name, modifier = Modifier.padding(10.dp)) })

            }
    }
}