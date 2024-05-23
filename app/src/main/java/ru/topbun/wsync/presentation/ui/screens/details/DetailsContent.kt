package ru.topbun.wsync.presentation.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.topbun.wsync.presentation.ui.screens.details.ui.CurrentWeather

@Composable
fun DetailsContent(component: DetailsComponent) {

}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        CurrentWeather()
    }
}
