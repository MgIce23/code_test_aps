package com.example.test23001_android_aps.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun InfoPopover(
    diet: String,
    onDismiss: () -> Unit
) {
    Popup(
        content = {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .graphicsLayer(translationY = 32f)
            ) {
                // Info content goes here
                Text(diet, modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        onDismissRequest = onDismiss
    )
}

