package com.jasrdeveloper.gymbilling.contract.home

import android.content.Context
import com.jasrdeveloper.gymbilling.view.common.components.BaseView

interface HomeActivityContract {

    interface View: BaseView {
        val context: Context
    }

    interface Presenter {
        fun onCreateHomeMainFragment()
    }

}