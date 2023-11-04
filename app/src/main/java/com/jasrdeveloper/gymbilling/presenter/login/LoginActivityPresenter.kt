package com.jasrdeveloper.gymbilling.presenter.login

import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.contract.login.LoginActivityContract
import com.jasrdeveloper.gymbilling.presenter.common.BasePresenter
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentCallback
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentNavigation

import com.jasrdeveloper.gymbilling.view.login.LoginActivity
import com.jasrdeveloper.gymbilling.view.login.LoginMainFragment

class LoginActivityPresenter() :BasePresenter<LoginActivityContract.View>(),
    BaseFragmentNavigation<LoginActivity>,
        LoginActivityContract.Presenter
{
    override var activity: LoginActivity? = null
    override val resourceActivity: Int = R.id.actHome_frmLayout
    override fun onCreateLoginMainFragment() {
       activity = (view as LoginActivity)
       val fragment = LoginMainFragment().createInstance(
            context = activity!!,
            dataFragment = null,
            fragmentCallback = object: BaseFragmentCallback{
                override fun <T> onFragmentSuccess(actionCode: Int, data: T?) {
                    TODO("Not yet implemented")
                }

                override fun onFragmentFailure() {
                    TODO("Not yet implemented")
                }

            }
        )
        activity!!.containerViewConfig(fragment.viewContainerConfig()!!)
        addFragment(fragment)
    }


}