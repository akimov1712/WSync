package ru.topbun.wsync.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import ru.topbun.wsync.R

private val gradientWeathers = mapOf(
    R.drawable.image_clean_d to createGradient(Color(254,202,155), Color(255,77,127)),
    R.drawable.image_cloud to createGradient(Color(81,215,233), Color(53,134,255)),
    R.drawable.image_fog to createGradient(Color(205,205,205), Color(104,113,122)),
    R.drawable.image_low_cloud_d to createGradient(Color(180,241,255), Color(53,134,255)),
    R.drawable.image_rain_d to createGradient(Color(177,210,243), Color(28,96,167)),
    R.drawable.image_rainfall to createGradient(Color(112,162,212), Color(27,87,149)),
    R.drawable.image_snow to createGradient(Color(16,64,141), Color(109,186,225)),
    R.drawable.image_thunder to createGradient(Color(109,160,211), Color(29,88,150)),
    R.drawable.image_very_clouds to createGradient(Color(79,133,188), Color(35,94,155)),
)

fun createGradient(
    firstColor: Color,
    secondColor: Color,
) = Brush.verticalGradient(listOf(firstColor,secondColor))

fun getGradientWithIcon(icon: Int): Brush = gradientWeathers[icon] ?: createGradient(
    Color(34,35,59), Color(7,33,84))