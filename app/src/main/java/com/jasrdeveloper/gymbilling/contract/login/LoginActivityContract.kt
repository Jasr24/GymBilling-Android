package com.jasrdeveloper.gymbilling.contract.login

import android.content.Context
import com.jasrdeveloper.gymbilling.view.common.components.BaseView

interface LoginActivityContract {
    interface View: BaseView {
        val context: Context
    }

    interface Presenter {
        fun onCreateLoginMainFragment()
    }
}