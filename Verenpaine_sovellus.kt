package com.example.omasovellus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Verenpaine() {
    var systolic by remember { mutableStateOf(TextFieldValue("")) }
    var diastolic by remember { mutableStateOf(TextFieldValue("")) }
    var tulos by rememberSaveable { mutableStateOf("") }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Verenpaine") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor =
                    MaterialTheme.colorScheme.primary)
            )
        }, content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    TextField(
                        value = systolic,
                        onValueChange = { systolic = it },
                        label = { Text("Diastlinen Arvo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = diastolic,
                        onValueChange = { diastolic = it },
                        label = { Text("Systolinen Arvo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {tulos = tulkitseVerenpaine(systolic.text, diastolic.text)})
                    {
                        Text("Laske")
                    }
                    Text("Tulos: $tulos")
                }
            }
        })
}
fun tulkitseVerenpaine(systolic: String, diastolic: String): String
{
    val systolicValue  = systolic.toIntOrNull() ?: 0
    val diastolicValue  = diastolic.toIntOrNull() ?: 0
    return when {
        systolicValue < 100 || diastolicValue < 60 -> "Matala verenpaine (Hypotensio)"
        systolicValue < 130 && diastolicValue < 85 -> "Normaali verenpaine"
        systolicValue in 130..139 || diastolicValue in 85..89 -> "Normaali verenpaine"
        systolicValue in 140..159 || diastolicValue in 90..99 -> "1. asteen verenpainetauti"
        systolicValue in 160..179 || diastolicValue in 100..109 -> "2. asteen verenpainetauti"
        else -> "Virheellinen verenpaine"
    }
}
