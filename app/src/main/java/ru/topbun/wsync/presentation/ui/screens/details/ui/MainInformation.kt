package ru.topbun.wsync.presentation.ui.screens.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.topbun.wsync.R
import ru.topbun.wsync.domain.entity.Forecast
import ru.topbun.wsync.domain.entity.HourWeather
import ru.topbun.wsync.utils.createFont
import ru.topbun.wsync.utils.getGradientVerticalWithIcon
import ru.topbun.wsync.utils.toShortString
import ru.topbun.wsync.utils.toTime
import java.util.Date

@Composable
fun CurrentInformationWeather(forecast: Forecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(getGradientVerticalWithIcon(forecast.currentWeather.iconRes))
    ) {
        Spacer(Modifier.height(48.dp))
        Header(forecast.date)
        Spacer(Modifier.height(20.dp))
        CityName(forecast.city)
        Spacer(Modifier.height(24.dp))
        BasicInformation(
            tempC = forecast.currentWeather.tempC.toInt(),
            conditionText = forecast.currentWeather.conditionText,
            iconRes = forecast.currentWeather.iconRes
        )
        Spacer(Modifier.height(24.dp))
        HoursWeather(forecast.thisDayWeather.hourlyForecast)
        Spacer(Modifier.height(24.dp))
        DifferenceTemp(
            minTempC = forecast.thisDayWeather.minTempC.toInt(),
            maxTempC = forecast.thisDayWeather.maxTempC.toInt(),
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun DifferenceTemp(
    minTempC: Int,
    maxTempC: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        InfoItem(
            titleText = "Минимум",
            iconText = "${minTempC}º",
            iconRes = R.drawable.ic_arrow_down
        )
        InfoItem(
            titleText = "Максимум",
            iconText = "${maxTempC}º",
            iconRes = R.drawable.ic_arrow_up
        )
    }
}

@Composable
private fun RowScope.InfoItem(
    titleText: String,
    iconText: String,
    iconRes: Int
) {
    Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = titleText,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = createFont(resId = R.font.nunito_bold)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = iconText,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = createFont(resId = R.font.nunito_bold)
            )
        }
    }
}

@Composable
private fun HoursWeather(list: List<HourWeather>) {
    LazyRow {
        items(
            items = list,
            key = {it.time}
        ) {
            ItemHoursWeather(it)
        }
    }

}

@Composable
fun ItemHoursWeather(
    hour: HourWeather
) {
    Column(modifier = Modifier.padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = hour.time.toTime(),
            fontFamily = createFont(R.font.nunito_bold),
            color = Color.White,
            fontSize = 14.sp
        )
        Image(
            modifier = Modifier
                .padding(top = 5.dp)
                .size(32.dp),
            painter = painterResource(hour.iconRes),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = "${hour.tempC.toInt()}º",
            fontFamily = createFont(R.font.nunito_bold),
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun BasicInformation(
    tempC: Int,
    conditionText: String,
    iconRes: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(start = 24.dp),
        ) {
            Text(
                text = "$tempC°",
                fontFamily = createFont(R.font.nunito_bold),
                color = Color.White,
                fontSize = 82.sp
            )
            Text(
                modifier = Modifier.width(240.dp),
                text = conditionText,
                fontFamily = createFont(R.font.nunito_bold),
                color = Color.White,
                fontSize = 32.sp
            )
        }
        Image(
            modifier = Modifier
                .padding(end = 24.dp)
                .size(130.dp)
                .align(Alignment.CenterEnd),
            painter = painterResource(iconRes),
            contentDescription = "Иконка погоды"
        )
    }
}

@Composable
private fun CityName(cityName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = cityName,
        color = Color.White,
        fontSize = 38.sp,
        textAlign = TextAlign.Center,
        fontFamily = createFont(R.font.pragati_bold)
    )
}

@Composable
private fun Header(date: Date) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.weight(1f),
            text = date.toShortString(),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = createFont(R.font.nunito_bold)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 20.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(
                modifier = Modifier.padding(4.dp),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Кнопка поиска",
                    tint = Color.White
                )
            }
        }

    }
}