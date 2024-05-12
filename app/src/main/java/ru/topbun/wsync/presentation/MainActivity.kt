package ru.topbun.wsync.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.topbun.wsync.data.network.api.ApiFactory
import ru.topbun.wsync.presentation.ui.theme.WSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = ApiFactory.weatherApiService
        CoroutineScope(Dispatchers.IO).launch {
            api.searchCity("Мо")
            api.loadCurrentWeather("Северное")
        }
        setContent {
            WSyncTheme {

            }
        }
    }
}