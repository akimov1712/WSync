package ru.topbun.wsync.presentation.ui.screens.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.topbun.wsync.presentation.ui.screens.details.DefaultDetailsComponent
import ru.topbun.wsync.presentation.ui.screens.details.OpenReasonDetails
import ru.topbun.wsync.presentation.ui.screens.splash.DefaultSplashComponent

class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val splashComponentFactory: DefaultSplashComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext{

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Splash,
        handleBackButton = true,
        childFactory = ::child,
    )

    fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child{
        return when(config){
            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    openReasonDetailsScreen = config.reason,
                    onClickToSearch = {},
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }

            is Config.Splash -> {
                val component = splashComponentFactory.create(
                    onPermissionCheckCompleted = {
                        navigation.push(Config.Details(
                            OpenReasonDetails.Empty
                        ))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Splash(component)
            }
        }
    }

    @Serializable
    sealed interface Config{
        @Serializable
        data class Details(val reason: OpenReasonDetails): Config

        @Serializable
        data object Splash: Config

    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}