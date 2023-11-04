package com.jasrdeveloper.gymbilling.common

import android.app.Application
import com.jasrdeveloper.gymbilling.di.DaggerDiComponent
import com.jasrdeveloper.gymbilling.di.DiComponent
import com.jasrdeveloper.gymbilling.di.modules.CommonModules
import com.jasrdeveloper.gymbilling.di.modules.InteractorModules
import com.jasrdeveloper.gymbilling.di.modules.PresenterModules
import com.jasrdeveloper.gymbilling.di.modules.RepositoryModules


class BaseApplication: Application() {

    private lateinit var diComponent: DiComponent

    override fun onCreate() {
        super.onCreate()

        diComponent = DaggerDiComponent.builder()
            .commonModules(CommonModules(context = this))
            .interactorModules(InteractorModules())
            .presenterModules(PresenterModules())
            .repositoryModules(RepositoryModules())
            .build()
    }

    fun injector() : DiComponent = diComponent
}