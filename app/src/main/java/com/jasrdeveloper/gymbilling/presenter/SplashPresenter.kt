package com.jasrdeveloper.gymbilling.presenter

import android.content.Intent
import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.contract.SplashContract
import com.jasrdeveloper.gymbilling.presenter.common.BasePresenter
import com.jasrdeveloper.gymbilling.view.SplashActivity
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentNavigation
import com.jasrdeveloper.gymbilling.view.home.HomeActivity
import javax.inject.Inject


class SplashPresenter @Inject constructor(
):  BasePresenter<SplashContract.View>(),
    BaseFragmentNavigation<SplashActivity>,
    SplashContract.Presenter{

    override var activity: SplashActivity? = null
    override val resourceActivity: Int = R.id.as_flMain

    init {
    }

    override fun load() {
        activity = (view as SplashActivity)
        goToHome()
    }

    override fun goToHome() {
        (activity as SplashActivity).startActivity(Intent(activity as SplashActivity, HomeActivity::class.java))
        (activity as SplashActivity).finish()
    }

}