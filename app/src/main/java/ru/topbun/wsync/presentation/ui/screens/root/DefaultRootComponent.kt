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
import ru.topbun.wsync.domain.entity.City
import ru.topbun.wsync.presentation.ui.screens.details.DefaultDetailsComponent
import ru.topbun.wsync.presentation.ui.screens.favorite.DefaultFavoriteComponent
import ru.topbun.wsync.presentation.ui.screens.search.DefaultSearchComponent
import ru.topbun.wsync.presentation.ui.screens.search.OpenReason

class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val searchComponentFactory: DefaultSearchComponent.Factory,
    private val favoriteComponentFactory: DefaultFavoriteComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext{

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Favorite,
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
                    city = config.city,
                    onClickToBack = {
                        navigation.pop()
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }
            Config.Favorite -> {
                val component = favoriteComponentFactory.create(
                    onClickToCity = {
                        navigation.push(Config.Details(it))
                    },
                    onClickToFavorite = {
                        navigation.push(Config.Search(OpenReason.AddToFavorite))
                    },
                    onClickToSearch = {
                        navigation.push(Config.Search(OpenReason.RegularSearch))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Favorite(component)
            }
            is Config.Search -> {
                val component = searchComponentFactory.create(
                    openReason = config.openReason,
                    onClickToBack = {
                        navigation.pop()
                    },
                    onSavedCity = {
                        navigation.pop()
                    },
                    onOpenForecast = {
                        navigation.push(Config.Details(it))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Search(component)
            }
        }
    }

    @Serializable
    sealed interface Config{

        @Serializable
        data object Favorite: Config

        @Serializable
        data class Details(val city: City): Config

        @Serializable
        data class Search(val openReason: OpenReason): Config

    }

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}