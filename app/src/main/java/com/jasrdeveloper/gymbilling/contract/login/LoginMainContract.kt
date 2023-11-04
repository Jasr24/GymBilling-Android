package com.jasrdeveloper.gymbilling.contract.login

import android.content.Context
import com.jasrdeveloper.gymbilling.view.common.components.BaseView
import com.jasrdeveloper.gymbilling.view.home.HomeMainFragment

interface LoginMainContract {
    interface View : BaseView {
        val fragmentContext: Context
    }

    interface FragmentPresenter {
        var fragment: HomeMainFragment?
    }

    interface InteractorInput {
        var interactorOutput: InteractorOutput?
    }

    interface InteractorOutput {
    }
}