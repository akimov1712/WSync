package ru.topbun.wsync.presentation.ui.screens.splash

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import ru.topbun.wsync.utils.PermissionCheckedGranted

@Composable
fun SplashContent(
    modifier: Modifier = Modifier,
    component: SplashComponent
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        component.permissionCheckCompleted()
    }
    LaunchedEffect(Unit) {
        delay(300)
        launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}