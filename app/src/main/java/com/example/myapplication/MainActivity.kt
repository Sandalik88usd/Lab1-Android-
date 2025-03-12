package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SelectionScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SelectionScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var selectedFaculty by remember { mutableStateOf("") }
    var selectedCourse by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Оберіть факультет:")
        RadioGroup(options = listOf("Факультет A", "Факультет B")) { selectedFaculty = it }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Оберіть курс:")
        RadioGroup(options = listOf("Курс 1", "Курс 2", "Курс 3")) { selectedCourse = it }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {
                if (selectedFaculty.isNotEmpty() && selectedCourse.isNotEmpty()) {
                    resultText = "Факультет: $selectedFaculty, Курс: $selectedCourse"
                } else {
                    Toast.makeText(context, "Будь ласка, оберіть факультет і курс", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("ОК")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { selectedFaculty = ""; selectedCourse = ""; resultText = "" }) {
                Text("Cancel")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = resultText)
    }
}

@Composable
fun RadioGroup(options: List<String>, onOptionSelected: (String) -> Unit) {
    var selectedOption by remember { mutableStateOf("") }

    Column {
        options.forEach { option ->
            Row {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = {
                        selectedOption = option
                        onOptionSelected(option)
                    }
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectionScreenPreview() {
    MyApplicationTheme {
        SelectionScreen()
    }
}