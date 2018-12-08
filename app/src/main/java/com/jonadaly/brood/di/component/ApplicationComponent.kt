package com.jonadaly.brood.di.component

import android.content.Context
import android.content.SharedPreferences
import com.jonadaly.brood.App
import com.jonadaly.brood.di.module.ApplicationModule
import com.jonadaly.brood.di.module.DatabaseModule
import com.jonadaly.brood.di.module.NetModule
import com.jonadaly.brood.ui.main.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetModule::class, DatabaseModule::class))
interface ApplicationComponent {

    fun app(): App

    fun context(): Context

    fun preferences(): SharedPreferences

    fun inject(mainActivityViewModel: MainActivityViewModel)

}