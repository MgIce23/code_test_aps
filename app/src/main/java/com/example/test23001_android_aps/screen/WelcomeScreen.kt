package com.example.test23001_android_aps.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test23001_android_aps.R
import com.example.test23001_android_aps.viewmodel.MainViewModel

@Composable
fun  HomeScreen(navController: NavController,mainViewModel: MainViewModel){

    Surface {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Text(text = "Welcome to Daily Vita", style = TextStyle(
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            ))

            Text(text = "Hello we are here to make your life healthier and happier",
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp))

            Image(
                modifier = Modifier
                    .weight(10f)
                    .fillMaxWidth(),
                painter =  painterResource(id = R.drawable.healthy),
                contentDescription = "welcome img",
                contentScale = ContentScale.Fit
                )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                text = "We will ask couple of questions to better understand your vatmin need")

            Spacer(modifier = Modifier.weight(0.5f))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange), // Change this color to your desired button color
                    contentColor =  colorResource(id = R.color.black) // Change this color to the desired text color
                ),
                onClick = {
                navController.navigate(Screen.Prioritize.route)
            }) {
                Text(text = "Get started", color = colorResource(id = R.color.white))
            }

        }
    }


}

@Preview
@Composable
fun HomeScreenPreview(){

    val viewModel = hiltViewModel<MainViewModel>()
    HomeScreen(rememberNavController(),viewModel)
}