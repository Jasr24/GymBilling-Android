package com.jasrdeveloper.gymbilling.view.common.base_fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.jasrdeveloper.gymbilling.common.BaseApplication
import com.jasrdeveloper.gymbilling.di.DiComponent
import com.jasrdeveloper.gymbilling.presenter.common.BaseFragmentPresenter
import com.jasrdeveloper.gymbilling.view.common.components.BaseView
import com.jasrdeveloper.gymbilling.view.common.components.ContainerViewConfigProperties
import com.jasrdeveloper.gymbilling.view.common.components.custom_dialog_spinner_load.CustomDialogSpinnerLoad
import java.lang.Exception

abstract class BaseFragment<V: BaseView, P: BaseFragmentPresenter<V>>: BaseView, Fragment() {
    var presenter: P? = null
    var viewContainerConfig: ContainerViewConfigProperties? = null
    lateinit var parentActivityContext: Context
    lateinit var drawerLayout: DrawerLayout
    private lateinit var _spinnerDialog: CustomDialogSpinnerLoad

    val spinnerDialog : CustomDialogSpinnerLoad
        get(){
            if(!::_spinnerDialog.isInitialized){
                _spinnerDialog = CustomDialogSpinnerLoad().create(parentActivityContext)
            }
            return _spinnerDialog
        }

    private var fragmentCallback: BaseFragmentCallback? = null

    abstract fun getFragmentView(): V?
    abstract fun createPresenter(): P?
    abstract fun <T>createInstance(
        context: Context,
        fragmentCallback: BaseFragmentCallback,
        dataFragment: T?
    ): BaseFragment<V, P>?
    abstract fun viewContainerConfig(): ContainerViewConfigProperties?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentActivityContext = this.context!!
        try {
            _spinnerDialog = CustomDialogSpinnerLoad().create(parentActivityContext)
        }catch (e: Exception){
            e.printStackTrace()
        }

        presenter = createPresenter()
        presenter?.view = getFragmentView()
    }

    override fun onDetach() {
        super.onDetach()
        presenter?.view = null
    }

    fun getFragmentCallback(): BaseFragmentCallback = fragmentCallback!!

    fun setFragmentCallback(fragmentCallback: BaseFragmentCallback){
        this.fragmentCallback = fragmentCallback
    }

    fun injectorBaseFragment(): DiComponent = (activity?.applicationContext as BaseApplication).injector()
}