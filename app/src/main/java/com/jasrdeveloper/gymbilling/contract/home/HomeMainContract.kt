package com.jasrdeveloper.gymbilling.contract.home

import android.content.Context
import com.jasrdeveloper.gymbilling.view.common.components.BaseView
import com.jasrdeveloper.gymbilling.view.home.HomeMainFragment

interface HomeMainContract {

    interface View : BaseView {
        val fragmentContext: Context
    }

    interface FragmentPresenter {
        var fragment: HomeMainFragment?
        fun goToSection(idSection:Int)
        fun listeners()
    }

    interface InteractorInput {
        var interactorOutput: InteractorOutput?
    }

    interface InteractorOutput {
    }

}