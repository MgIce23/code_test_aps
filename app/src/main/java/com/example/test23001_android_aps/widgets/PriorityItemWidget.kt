package com.example.test23001_android_aps.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test23001_android_aps.domain.HealthConcern
import com.example.test23001_android_aps.screen.Screen

@Composable
fun PrioritizeItems(
    healthConcern: HealthConcern
){
    Surface {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.background(Color.Black).padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))){
                    Text(text = healthConcern.name, color = Color.White)
                }

                Icon(imageVector = Icons.Default.List, contentDescription = "icon")
            }
        }
    }
}

@Preview
@Composable
fun PrioritizeItemPreview(){
    PrioritizeItems(healthConcern = HealthConcern(0,"Sleep"))
}