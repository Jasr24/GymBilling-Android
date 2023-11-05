package com.jasrdeveloper.gymbilling.presenter.home

import com.jasrdeveloper.gymbilling.contract.home.HomeMainContract
import com.jasrdeveloper.gymbilling.presenter.common.BaseFragmentPresenter
import com.jasrdeveloper.gymbilling.view.home.HomeMainFragment
import javax.inject.Inject

class HomeMainPresenter @Inject constructor(
):
    BaseFragmentPresenter<HomeMainContract.View>(),
    HomeMainContract.FragmentPresenter,
    HomeMainContract.InteractorOutput {


    override var fragment: HomeMainFragment? = null

    init {
    }

    override fun load() {
        fragment = (view as HomeMainFragment)
    }

    override fun goToSection(idSection: Int) {

    }
}
