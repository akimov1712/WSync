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
import ru.topbun.wsync.utils.createFont
import ru.topbun.wsync.utils.createGradient

@Composable
fun CurrentWeather() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(createGradient(Color(254, 202, 155), Color(255, 77, 127)))
    ) {
        Spacer(Modifier.height(24.dp))
        Header()
        Spacer(Modifier.height(24.dp))
        CityName()
        Spacer(Modifier.height(24.dp))
        BasicInformation()
        Spacer(Modifier.height(24.dp))
        HoursWeather()
        Spacer(Modifier.height(24.dp))
        DifferenceTemp()
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun DifferenceTemp() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        IconWithText(
            titleText = "Минимум",
            iconText = "13º",
            iconRes = R.drawable.ic_arrow_down
        )
        IconWithText(
            titleText = "Максимум",
            iconText = "18º",
            iconRes = R.drawable.ic_arrow_up
        )
    }
}

@Composable
private fun RowScope.IconWithText(
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
private fun HoursWeather() {
    LazyRow{
        repeat(24) {
            item {
                ItemHoursWeather()
            }
        }
    }
}

@Composable
fun ItemHoursWeather() {
    Column(modifier = Modifier.padding(horizontal = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "03:00",
            fontFamily = createFont(R.font.nunito_bold),
            color = Color.White,
            fontSize = 14.sp
        )
        Image(
            modifier = Modifier
                .padding(top = 5.dp)
                .width(32.dp),
            painter = painterResource(R.drawable.image_clean_n),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = "+12º",
            fontFamily = createFont(R.font.nunito_bold),
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun BasicInformation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(start = 42.dp),
        ) {
            Text(
                text = "14°",
                fontFamily = createFont(R.font.nunito_bold),
                color = Color.White,
                fontSize = 82.sp
            )
            Text(
                modifier = Modifier.width(240.dp),
                text = "Солнечно c прояснениями",
                fontFamily = createFont(R.font.nunito_bold),
                color = Color.White,
                fontSize = 32.sp
            )
        }
        Image(
            modifier = Modifier
                .padding(end = 24.dp)
                .size(130.dp)
                .align(Alignment.TopEnd),
            painter = painterResource(R.drawable.image_clean_d),
            contentDescription = "Иконка погоды"
        )
    }
}

@Composable
private fun CityName() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Москва",
        color = Color.White,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        fontFamily = createFont(R.font.pragati_bold)
    )
}

@Composable
private fun Header() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.weight(1f),
            text = "NOV 25",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = createFont(R.font.nunito_bold)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 24.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Кнопка поиска",
                    tint = Color.White
                )
            }
        }

    }
}