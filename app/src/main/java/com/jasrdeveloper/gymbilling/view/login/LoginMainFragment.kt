package com.jasrdeveloper.gymbilling.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jasrdeveloper.gymbilling.contract.login.LoginMainContract
import com.jasrdeveloper.gymbilling.databinding.FragmentLoginMainBinding
import com.jasrdeveloper.gymbilling.presenter.login.LoginMainPresenter
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragment
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentCallback
import com.jasrdeveloper.gymbilling.view.common.components.BaseFragmentOnBackPressed
import com.jasrdeveloper.gymbilling.view.common.components.ContainerViewConfigProperties
import javax.inject.Inject

class LoginMainFragment:
    BaseFragment<LoginMainContract.View, LoginMainPresenter>(),
LoginMainContract.View, BaseFragmentOnBackPressed {

    @Inject
    lateinit var loginMainPresenter: LoginMainPresenter
    override val fragmentContext: Context get() = parentActivityContext

    override fun getFragmentView(): LoginMainContract.View = this
    private var _binding: FragmentLoginMainBinding? = null
    val binding get() = _binding!!
    override fun createPresenter(): LoginMainPresenter = loginMainPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectorBaseFragment().injectFragment(this)
        _binding = FragmentLoginMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun <T> createInstance(
        context: Context,
        fragmentCallback: BaseFragmentCallback,
        dataFragment: T?
    ): BaseFragment<LoginMainContract.View, LoginMainPresenter> {
        val args = Bundle()
        this.arguments = args
        this.setFragmentCallback(fragmentCallback)
        this.parentActivityContext = context
        this.viewContainerConfig = viewContainerConfig()
        return this
    }

    override fun viewContainerConfig(): ContainerViewConfigProperties = ContainerViewConfigProperties(title = "",showTitle = false, showHeader = false)

    override fun onResume() {
        super.onResume()
        println("==> ScoreMainFragmentResume")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginMainPresenter.load()
    }

    override fun onBackPressed(): Boolean {
        getFragmentCallback().onFragmentSuccess(1, null)
        return false
    }
}