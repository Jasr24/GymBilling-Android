package com.jasrdeveloper.gymbilling.contract

import android.content.Context
import com.jasrdeveloper.gymbilling.view.common.components.BaseView

interface SplashContract {

    interface View : BaseView {
        val context: Context
    }

    interface Presenter {
        fun load()
        fun goToHome()
    }


    interface InteractorInput{
        var interactorOutput: InteractorOutput?
    }

    interface InteractorOutput{
    }

}