package ru.topbun.wsync.presentation.ui.screens.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.topbun.wsync.R
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.Weather
import ru.topbun.wsync.presentation.ui.screens.details.DetailsComponent
import ru.topbun.wsync.presentation.ui.screens.details.DetailsStore
import ru.topbun.wsync.utils.createFont
import ru.topbun.wsync.utils.toTime

@Composable
fun DetailsContent(component: DetailsComponent) {
    val stateScreen by component.model.collectAsState()
    when(val state = stateScreen.forecastState){
        DetailsStore.State.ForecastState.Error -> {
            Text(text = "Ошибка")
        }
        DetailsStore.State.ForecastState.Initial -> {}
        DetailsStore.State.ForecastState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){ CircularProgressIndicator()}
        }
        is DetailsStore.State.ForecastState.Success -> {
            DetailContentSuccess(state.forecast)
        }
    }
}

@Composable
private fun DetailContentSuccess(forecast: Forecast) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        CurrentInformationWeather(forecast)
        OtherInformationWeather(forecast)
    }
}
