package com.jasrdeveloper.gymbilling.view

import android.content.Context
import android.os.Bundle
import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.contract.SplashContract
import com.jasrdeveloper.gymbilling.presenter.SplashPresenter
import com.jasrdeveloper.gymbilling.view.common.components.BaseActivity
import com.jasrdeveloper.gymbilling.view.common.components.ContainerViewConfigProperties
import javax.inject.Inject

class SplashActivity :
    BaseActivity<SplashContract.View, SplashPresenter>(),
    SplashContract.View {


    @Inject
    lateinit var splashPresenter: SplashPresenter
    override val context: Context = this

    override fun getView(): SplashContract.View? = this
    override fun createPresenter(): SplashPresenter? = splashPresenter
    override fun containerViewConfig(containerViewConfig: ContainerViewConfigProperties) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        injectorBaseActivity().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        android.os.Handler().postDelayed({
            splashPresenter.load()
          },3000)

    }

}