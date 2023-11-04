package com.jasrdeveloper.gymbilling.presenter.home

import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.contract.home.HomeActivityContract
import com.jasrdeveloper.gymbilling.presenter.common.BasePresenter
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentCallback
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentNavigation
import com.jasrdeveloper.gymbilling.view.home.HomeActivity
import com.jasrdeveloper.gymbilling.view.home.HomeMainFragment

class HomeActivityPresenter:
    BasePresenter<HomeActivityContract.View>(),
    BaseFragmentNavigation<HomeActivity>,
    HomeActivityContract.Presenter {

    private lateinit var dataConsult: Number
    override var activity: HomeActivity? = null
    override val resourceActivity: Int = R.id.actHome_frmLayout

    override fun onCreateHomeMainFragment() {
        activity = (view as HomeActivity)
        val fragment = HomeMainFragment().createInstance(
            context = activity!!,
            dataFragment = null,
            fragmentCallback = object: BaseFragmentCallback {
                override fun <T> onFragmentSuccess(actionCode: Int, data: T?) {
                    when(actionCode){
                    }
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