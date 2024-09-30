package com.example.omasovellus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.omasovellus.ui.theme.primaryContainerDark
import com.example.omasovellus.ui.theme.primaryDark

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Polttoaine() {
    var polttoaineprice by remember { mutableStateOf(TextFieldValue("1.7")) }
    var tulos by remember { mutableStateOf("") }
    var km by remember { mutableStateOf(TextFieldValue("100")) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.bensa_laskuri)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = primaryDark
                )
            )
        }, content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), color = MaterialTheme.colorScheme.background
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    , verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    OutlinedTextField(
                        value = polttoaineprice,
                        onValueChange = { polttoaineprice = it },
                        label = { Text(stringResource(R.string.polttoaine)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        value = km,
                        onValueChange = { km = it },
                        label = { Text(stringResource(R.string.kilometrit)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Button(onClick = {
                        val price = polttoaineprice.text.toDoubleOrNull() ?: 0.0
                        val kmvalue = km.text.toDoubleOrNull() ?: 0.0
                        tulos = (price * kmvalue *5 / 100).toString()

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = primaryContainerDark
                    )
                    ) {
                        Text("Laske hinta")
                    }
                    Text("Tulos: $tulos")
                }

            }
        }
    )

}
