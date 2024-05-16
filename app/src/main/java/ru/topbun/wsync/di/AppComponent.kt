package ru.topbun.wsync.di

import android.content.Context
import com.arkivanov.mvikotlin.core.store.StoreFactory
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        LocalModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        StoreFactoryModule::class
    ]
)
interface AppComponent {


    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }


}