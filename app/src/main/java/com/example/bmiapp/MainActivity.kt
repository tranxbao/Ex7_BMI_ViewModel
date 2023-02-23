package com.example.bmiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.bmiapp.ui.theme.BmiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                BodyMassIndexApp(viewModel = BodyMassIndexViewModel())
                }
            }
        }
    }
}

@Composable
fun BodyMassIndexApp(viewModel: BodyMassIndexViewModel) {

    val heightState = remember { mutableStateOf("") }
    val weightState = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Body Mass Index") }
            )
        }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            TextField(
                value = heightState.value,
                onValueChange = { value ->
                    heightState.value = value
                    viewModel.height = value.toFloatOrNull() ?: 0f
                    viewModel.calculateBMI()
                },
                label = { Text("Height (in meters)") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = weightState.value,
                onValueChange = { value ->
                    weightState.value = value
                    viewModel.weight = value.toFloatOrNull() ?: 0f
                    viewModel.calculateBMI()
                },
                label = { Text("Weight (in kg)") },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "BMI is: ${viewModel.bmi}",
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

        }
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiAppTheme {
        BodyMassIndexApp(viewModel = BodyMassIndexViewModel())
    }
}