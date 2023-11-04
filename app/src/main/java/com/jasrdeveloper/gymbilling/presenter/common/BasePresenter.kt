package com.jasrdeveloper.gymbilling.presenter.common

import com.jasrdeveloper.gymbilling.view.common.components.BaseView

open class BasePresenter <V: BaseView>{
    var view: V? = null
}