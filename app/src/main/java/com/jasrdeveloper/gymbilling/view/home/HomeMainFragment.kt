package com.jasrdeveloper.gymbilling.view.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jasrdeveloper.gymbilling.contract.home.HomeMainContract
import com.jasrdeveloper.gymbilling.databinding.FragmentHomeMainBinding
import com.jasrdeveloper.gymbilling.presenter.home.HomeMainPresenter
import com.jasrdeveloper.gymbilling.utils.enums.LayoutViewTypes
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragment
import com.jasrdeveloper.gymbilling.view.common.base_fragment.BaseFragmentCallback
import com.jasrdeveloper.gymbilling.view.common.components.BaseFragmentOnBackPressed
import com.jasrdeveloper.gymbilling.view.common.components.ContainerViewConfigProperties
import javax.inject.Inject

class HomeMainFragment :
    BaseFragment<HomeMainContract.View, HomeMainPresenter>(),
    HomeMainContract.View, BaseFragmentOnBackPressed {

    @Inject
    lateinit var homeMainPresenter: HomeMainPresenter
    override val fragmentContext: Context get() = parentActivityContext
    override fun getFragmentView(): HomeMainContract.View = this
    override fun createPresenter(): HomeMainPresenter = homeMainPresenter
    private var _binding: FragmentHomeMainBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        injectorBaseFragment().injectFragment(this)
        _binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun <T> createInstance(
        context: Context,
        fragmentCallback: BaseFragmentCallback,
        dataFragment: T?
    ): BaseFragment<HomeMainContract.View, HomeMainPresenter> {
        val args = Bundle()
        this.arguments = args
        this.setFragmentCallback(fragmentCallback)
        this.parentActivityContext = context
        this.viewContainerConfig = viewContainerConfig()
        return this
    }

    override fun viewContainerConfig(): ContainerViewConfigProperties =
        ContainerViewConfigProperties(title = "",
            showTitle = false,
            headerType = LayoutViewTypes.HOME_LAYOUT)

    override fun onResume() {
        super.onResume()
        println("==> ScoreMainFragmentResume")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMainPresenter.load()

        spinnerDialog.showSpinner();
        android.os.Handler().postDelayed({
            spinnerDialog.hideSpinner();
        },3000)

    }

    override fun onBackPressed(): Boolean = false

}