package com.jasrdeveloper.gymbilling.di

import com.jasrdeveloper.gymbilling.di.modules.*
import com.jasrdeveloper.gymbilling.view.SplashActivity
import com.jasrdeveloper.gymbilling.view.home.HomeActivity
import com.jasrdeveloper.gymbilling.view.home.HomeMainFragment
import com.jasrdeveloper.gymbilling.view.login.LoginActivity
import com.jasrdeveloper.gymbilling.view.login.LoginMainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CommonModules::class,
        DatabaseModules::class,
        InteractorModules::class,
        PresenterModules::class,
        RepositoryModules::class
    ]
)
interface DiComponent {

    /* .: ACTIVITY REGION :. */
    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(loginActivity: LoginActivity)

    /* .: FRAGMENT REGION :. */
    fun injectFragment(homeMainFragment: HomeMainFragment)
    fun injectFragment(loginMainFragment: LoginMainFragment)

}