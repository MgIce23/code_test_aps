package com.example.test23001_android_aps.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ButtonWidget(
    modifier: Modifier,
    onClickBack : () -> Unit,
    onClickNext : () -> Unit
){
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically){

        NewsTextButton(text = "Back" ) {
            onClickBack()
        }
        NewsButton(text = "Next" ) {
            onClickNext()
        }
    }
}