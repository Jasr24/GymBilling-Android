package com.jasrdeveloper.gymbilling.di.modules

import com.jasrdeveloper.gymbilling.presenter.SplashPresenter
import com.jasrdeveloper.gymbilling.presenter.home.HomeActivityPresenter
import com.jasrdeveloper.gymbilling.presenter.login.LoginActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModules {

    /* .: SPLASH REGION :. */
    @Provides
    fun provideSplashPresenter(
    ) = SplashPresenter()

    /* .: HOME REGION :. */
    @Provides
    fun provideHomePresenter() = HomeActivityPresenter()

    /* .: LOGIN REGION :. */
    @Provides
    fun provideLoginPresenter() = LoginActivityPresenter()


}
