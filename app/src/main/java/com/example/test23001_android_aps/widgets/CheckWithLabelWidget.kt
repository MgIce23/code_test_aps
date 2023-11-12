package com.example.test23001_android_aps.widgets

import android.widget.CheckBox
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.test23001_android_aps.domain.Diet

@Composable
fun CheckWithLabel(
    diet: Diet,
    isCheck : Boolean,
    onCheckChange: (Diet) ->Unit,
    onClickInfo:(String) -> Unit
){

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
        ){
        Checkbox(checked = isCheck, onCheckedChange = {
            onCheckChange(diet)
        })
        Text(text = diet.name)
        if(diet.id != 0) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "info",
                modifier = Modifier.clickable {
                    onClickInfo(diet.tool_tip)
                })
        }

    }
}