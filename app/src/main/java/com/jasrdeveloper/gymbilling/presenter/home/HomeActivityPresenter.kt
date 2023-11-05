package com.jasrdeveloper.gymbilling.presenter.home

import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.contract.home.HomeActivityContract
import com.jasrdeveloper.gymbilling.presenter.common.BasePresenter
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentCallback
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentNavigation
import com.jasrdeveloper.gymbilling.view.home.HomeActivity
import com.jasrdeveloper.gymbilling.view.home.HomeMainFragment
import com.jasrdeveloper.gymbilling.view.login.LoginActivity

class HomeActivityPresenter:
    BasePresenter<HomeActivityContract.View>(),
    BaseFragmentNavigation<HomeActivity>,
    HomeActivityContract.Presenter {

    override var activity: HomeActivity? = null
    override val resourceActivity: Int = R.id.actHome_frmLayout

    override fun load() {
        activity!!.findViewById<ImageView>(R.id.flm_imgBack).visibility = View.GONE
        activity!!.findViewById<ImageView>(R.id.am_img_home).visibility = View.GONE
        activity!!.findViewById<ImageView>(R.id.am_img_logout).visibility = View.GONE
        activity!!.findViewById<ImageView>(R.id.am_img_menu).visibility = View.GONE
    }

    override fun onCreateHomeMainFragment() {
        activity = (view as HomeActivity)
        val fragment = HomeMainFragment().createInstance(
            context = activity!!,
            dataFragment = null,
            fragmentCallback = object: BaseFragmentCallback {
                override fun <T> onFragmentSuccess(actionCode: Int, data: T?) {
                    val context = activity
                    if (context != null) {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                        context.finish()
                    }
                }

                override fun onFragmentFailure() {
                    //TODO("Not yet implemented")
                }
            }
        )
        activity!!.containerViewConfig(fragment.viewContainerConfig()!!)
        replaceFragment(fragment)
    }

}