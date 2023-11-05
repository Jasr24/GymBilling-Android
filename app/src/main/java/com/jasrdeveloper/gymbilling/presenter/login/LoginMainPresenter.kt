package com.jasrdeveloper.gymbilling.presenter.login

import com.jasrdeveloper.gymbilling.contract.login.LoginMainContract
import com.jasrdeveloper.gymbilling.presenter.common.BaseFragmentPresenter
import com.jasrdeveloper.gymbilling.view.login.LoginMainFragment
import javax.inject.Inject


class LoginMainPresenter @Inject constructor():
    BaseFragmentPresenter<LoginMainContract.View>(),
    LoginMainContract.FragmentPresenter,
    LoginMainContract.InteractorOutput  {

    override var fragment: LoginMainFragment? = null

    init {

    }

    override fun load() {
        fragment = (view as LoginMainFragment)
        listener()
    }

    override fun listener() {
        fragment!!.binding.flmBtCancel.setOnClickListener{
            fragment!!.getFragmentCallback().onFragmentSuccess(1, null)
        }
    }
}