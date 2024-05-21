package ru.topbun.wsync.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.topbun.wsync.domain.repository.WeatherRepository
import ru.topbun.wsync.getComponent
import ru.topbun.wsync.presentation.ui.screens.root.DefaultRootComponent
import ru.topbun.wsync.presentation.ui.screens.root.RootContent
import javax.inject.Inject
import kotlin.coroutines.resume

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        application.getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            RootContent(
                component = rootComponentFactory.create(defaultComponentContext())
            )
        }
    }

}