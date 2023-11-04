package com.jasrdeveloper.gymbilling.presenter.common

import com.jasrdeveloper.gymbilling.view.common.components.BaseView

abstract class BaseFragmentPresenter <V: BaseView> {
    var view: V? = null
    abstract fun load()
}