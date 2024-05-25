package ru.topbun.wsync.presentation.ui.screens.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.topbun.wsync.R
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.Weather
import ru.topbun.wsync.utils.createFont
import ru.topbun.wsync.utils.getGradientHorizontalWithIcon
import ru.topbun.wsync.utils.toLongString
import ru.topbun.wsync.utils.toTime

@Composable
fun OtherInformationWeather(forecast: Forecast) {
    SecondaryInformation(forecast)
    Forecast(forecast)
}

@Composable
private fun Forecast(forecast: Forecast) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp)){
        Text(
            text = "Прогноз на 3 дня",
            color = Color.Black,
            fontFamily = createFont(R.font.nunito_bold),
            fontSize = 24.sp
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            forecast.upcoming.forEach {
                UpcomingItem(it)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun UpcomingItem(weather: Weather) {
    Card(
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(getGradientHorizontalWithIcon(weather.iconRes))
                .padding(vertical = 12.dp, horizontal = 18.dp)
        ) {
            Image(
                modifier = Modifier.size(44.dp),
                painter = painterResource(weather.iconRes),
                contentDescription = "Картинка состояния погоды"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = weather.date.toLongString(),
                    color = Color.White,
                    fontFamily = createFont(R.font.nunito_bold),
                    fontSize = 20.sp
                )
                Text(
                    text = weather.conditionText.formatConditionText(),
                    color = Color(255, 255, 255, 153),
                    fontFamily = createFont(R.font.nunito_bold),
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row {
                DifferenceTempItem(
                    iconRes = R.drawable.ic_arrow_up,
                    iconText = "${weather.maxTempC.toInt()}º"
                )
                Spacer(modifier = Modifier.width(4.dp))
                DifferenceTempItem(
                    iconRes = R.drawable.ic_arrow_down,
                    iconText = "${weather.minTempC.toInt()}º"
                )
            }
        }
    }
}

@Composable
private fun DifferenceTempItem(
    iconRes: Int,
    iconText: String
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.scale(0.7f),
            painter = painterResource(iconRes),
            colorFilter = ColorFilter.tint(Color(0xf2,0xf2,0xf1)),
            contentDescription = null,
        )
        Text(
            text = iconText,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = createFont(resId = R.font.nunito_bold)
        )
    }
}

@Composable
private fun SecondaryInformation(forecast: Forecast) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .heightIn(max = 1000.dp),
        contentPadding = PaddingValues(top = 24.dp),

        ) {
        item {
            val sunrise = forecast.thisDayWeather.sunrise
            ItemInfo(
                titleText = "Рассвет",
                iconRes = R.drawable.ic_sunrise,
                iconText = sunrise.toTime()
            )
        }
        item {
            val sunset = forecast.thisDayWeather.sunset
            ItemInfo(
                titleText = "Закат",
                iconRes = R.drawable.ic_sunset,
                iconText = sunset.toTime()
            )
        }
        item {
            val wind = forecast.currentWeather.windSpeed
            ItemInfo(
                titleText = "Ветер",
                iconRes = R.drawable.ic_wind,
                iconText = "$wind м/c"
            )
        }
        item {
            val pressure = forecast.currentWeather.pressure
            ItemInfo(
                titleText = "Давление",
                iconRes = R.drawable.ic_pressure,
                iconText = "$pressure мрс"
            )
        }
        item {
            val precip = forecast.currentWeather.precipMm
            ItemInfo(
                titleText = "Осадки",
                iconRes = R.drawable.ic_precip,
                iconText = "${precip.toInt()} мм"
            )
        }
        item {
            val humidity = forecast.currentWeather.humidityPercent
            ItemInfo(
                titleText = "Влажность",
                iconRes = R.drawable.ic_humidity,
                iconText = "$humidity %"
            )
        }
    }
}


@Composable
private fun ItemInfo(
    titleText: String,
    iconRes: Int,
    iconText: String
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = titleText,
            color = Color.Black,
            fontFamily = createFont(R.font.nunito_medium),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        IconWithText(iconRes, iconText)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun IconWithText(
    iconRes: Int,
    iconText: String
){
    Row {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = iconText,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = createFont(resId = R.font.nunito_medium)
        )
    }
}

fun String.formatConditionText() = if (this.length >= 14) "${this.take(14)}..." else this
