package ru.topbun.wsync.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.topbun.wsync.data.network.api.WeatherApiService
import ru.topbun.wsync.presentation.MainActivity

@ApplicationScope
@Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class,
        StoreFactoryModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)


    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }


}