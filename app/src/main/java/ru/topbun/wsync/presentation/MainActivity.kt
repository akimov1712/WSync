package ru.topbun.wsync.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.topbun.wsync.data.network.api.ApiFactory
import ru.topbun.wsync.getComponent
import ru.topbun.wsync.presentation.ui.screens.root.DefaultRootComponent
import ru.topbun.wsync.presentation.ui.screens.root.DefaultRootComponent_Factory
import ru.topbun.wsync.presentation.ui.screens.root.RootContent
import ru.topbun.wsync.presentation.ui.theme.WSyncTheme

class MainActivity : ComponentActivity() {

    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        application.getComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            RootContent(
                rootComponentFactory.create(defaultComponentContext())
            )
        }
    }
}