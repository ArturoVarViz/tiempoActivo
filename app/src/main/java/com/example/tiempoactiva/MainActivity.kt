package com.example.tiempoactiva

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiempoactiva.ui.theme.TiempoActivaTheme

class MainActivity : ComponentActivity() {
    private var startTime: Long = 0
    private var remember = mutableStateOf(0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiempoActivaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RandomButton()
                    Greeting("Android", remember.value)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startTime = System.currentTimeMillis()
    }

    override fun onPause() {
        super.onPause()
        remember.value = (System.currentTimeMillis() - startTime) / 1000
        Log.d("condor", "La aplicación estuvo activa durante ${remember.value} segundos")
    }
}

@Composable
fun RandomButton() {
    val randomNum = remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { randomNum.value = (1..10).random() }) {
            Text("Generar número aleatorio")
        }
        Text(text = "Número aleatorio: ${randomNum.value}")
    }
}

@Composable
fun Greeting(name: String, activeTime: Long, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
           text = "",
       modifier = modifier
       )
        if (activeTime > 0) {
            Text(
                text = "La última vez, la aplicación estuvo activa durante $activeTime segundos",
                modifier = modifier.padding(top = 100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TiempoActivaTheme {
        Greeting("Android", 0)
    }
}
