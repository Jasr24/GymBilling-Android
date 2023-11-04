package com.jasrdeveloper.gymbilling.presenter.login

import com.jasrdeveloper.gymbilling.contract.login.LoginMainContract
import com.jasrdeveloper.gymbilling.presenter.common.BaseFragmentPresenter
import com.jasrdeveloper.gymbilling.view.home.HomeMainFragment
import javax.inject.Inject


class LoginMainPresenter @Inject constructor():
    BaseFragmentPresenter<LoginMainContract.View>(),
    LoginMainContract.FragmentPresenter,
    LoginMainContract.InteractorOutput  {

    override var fragment: HomeMainFragment? = null

    init {
    }

    override fun load() {
        fragment = (view as HomeMainFragment)
        //TODO: pendiente implementar flujo
    }
}