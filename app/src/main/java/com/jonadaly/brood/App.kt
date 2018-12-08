package com.jonadaly.brood

import com.jonadaly.brood.di.component.DaggerApplicationComponent
import com.jonadaly.brood.di.module.ApplicationModule

class App : android.app.Application() {

    val component by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}