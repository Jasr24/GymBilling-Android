package com.jasrdeveloper.gymbilling.view.common.base_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.presenter.common.BaseFragmentPresenter
import com.jasrdeveloper.gymbilling.view.MainActivity
import com.jasrdeveloper.gymbilling.view.common.components.BaseView

interface BaseFragmentNavigation<T> {

    var activity: T?
    val resourceActivity: Int

    fun <V : BaseView, P : BaseFragmentPresenter<V>> replaceFragment(fragment: BaseFragment<V, P>?) {
        val fm = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
        fm.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.enter_anim, R.anim.exit_anim)
        fm.replace(resourceActivity, fragment as Fragment, fragment.javaClass.name)
        fm.addToBackStack(fragment.javaClass.name)
        fm.commit()
    }

    fun <V : BaseView, P : BaseFragmentPresenter<V>> addFragment(fragment: BaseFragment<V, P>?) {
        val fm = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
        fm.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.enter_anim, R.anim.exit_anim)
        fm.add(resourceActivity, fragment as Fragment, fragment.javaClass.name)
        fm.addToBackStack(fragment.javaClass.name)
            .setReorderingAllowed(true)
        fm.commit()
    }

    fun removeFragment() {
        val fragment = getCurrentFragmentToRemove((activity as FragmentActivity).supportFragmentManager)!!
        val fm = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
        fm.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.enter_anim, R.anim.exit_anim)
        fm.remove(fragment as Fragment)
        fm.commit()
    }

    fun backFragment(finish: Boolean) {
        val count = (activity as FragmentActivity).supportFragmentManager.backStackEntryCount
        if (count == 1 || count == 0 || finish) {
            (activity as FragmentActivity).finish()
        } else {
            (activity as FragmentActivity).supportFragmentManager.popBackStack()
            val fragment = getCurrentFragment((activity as FragmentActivity).supportFragmentManager)!!
            fragment.viewContainerConfig()
            (activity as MainActivity<*, *>).containerViewConfig(fragment.viewContainerConfig()!!)
        }
    }

    fun getCurrentFragment(fragmentManager: FragmentManager): BaseFragment<*, *>? {
        val fragmentTag: String? = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 2).name
        if (fragmentTag != null) {
            return (fragmentManager.findFragmentByTag(fragmentTag) as BaseFragment<*, *>)
        }
        return null
    }

    fun getCurrentFragmentToRemove(fragmentManager: FragmentManager): BaseFragment<*, *>? {
        val fragmentTag: String? = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
        if (fragmentTag != null) {
            return (fragmentManager.findFragmentByTag(fragmentTag) as BaseFragment<*, *>)
        }
        return null
    }

}