package ru.topbun.wsync.presentation.ui.screens.splash

import android.Manifest
import android.provider.CalendarContract.Colors
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.topbun.wsync.R

@Composable
fun SplashContent(
    modifier: Modifier = Modifier,
    component: SplashComponent
) {
    requestPermission(component)
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.image_logo),
            contentDescription = "Логотип",
            modifier = Modifier.size(156.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}


@Composable
private fun requestPermission(component: SplashComponent) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            component.permissionCheckCompleted()
        }
    LaunchedEffect(Unit) {
        delay(300)
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }
}
