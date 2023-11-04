package com.jasrdeveloper.gymbilling.view.common.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jasrdeveloper.gymbilling.common.BaseApplication
import com.jasrdeveloper.gymbilling.di.DiComponent
import com.jasrdeveloper.gymbilling.presenter.common.BasePresenter

abstract class BaseActivity<V: BaseView, P: BasePresenter<V>>: AppCompatActivity(), BaseView {

    var presenter: P? = null
    abstract fun getView(): V?
    abstract fun createPresenter(): P?
    abstract fun containerViewConfig(containerViewConfig: ContainerViewConfigProperties)

    fun injectorBaseActivity(): DiComponent = (this.applicationContext as BaseApplication).injector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.view = getView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.view = null
    }
}