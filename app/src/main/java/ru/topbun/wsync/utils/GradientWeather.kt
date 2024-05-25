package ru.topbun.wsync.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import ru.topbun.wsync.R

private data class Gradient(
    val firstColor: Color,
    val secondColor: Color,
)

private val gradientWeathers = mapOf(
    R.drawable.image_clean_d to Gradient(Color(254,202,155), Color(255,77,127)),
    R.drawable.image_cloud to Gradient(Color(81,215,233), Color(53,134,255)),
    R.drawable.image_fog to Gradient(Color(205,205,205), Color(104,113,122)),
    R.drawable.image_low_cloud_d to Gradient(Color(180,241,255), Color(53,134,255)),
    R.drawable.image_rain_d to Gradient(Color(177,210,243), Color(28,96,167)),
    R.drawable.image_rainfall to Gradient(Color(112,162,212), Color(27,87,149)),
    R.drawable.image_snow to Gradient(Color(16,64,141), Color(109,186,225)),
    R.drawable.image_thunder to Gradient(Color(109,160,211), Color(29,88,150)),
    R.drawable.image_very_clouds to Gradient(Color(79,133,188), Color(35,94,155)),
)


fun getGradientVerticalWithIcon(icon: Int): Brush{
    val gradient = gradientWeathers[icon] ?: Gradient(
        Color(34,35,59), Color(7,33,84))
    return Brush.verticalGradient(listOf(gradient.firstColor,gradient.secondColor))
}

fun getGradientHorizontalWithIcon(icon: Int): Brush{
    val gradient = gradientWeathers[icon] ?: Gradient(
        Color(34,35,59), Color(7,33,84))
    return Brush.linearGradient(
        listOf(gradient.secondColor,gradient.firstColor))
}