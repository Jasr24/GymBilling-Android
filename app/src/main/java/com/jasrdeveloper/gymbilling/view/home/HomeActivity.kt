package com.jasrdeveloper.gymbilling.view.home

import android.content.Context
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.contract.home.HomeActivityContract
import com.jasrdeveloper.gymbilling.databinding.ActivityHomeBinding
import com.jasrdeveloper.gymbilling.presenter.home.HomeActivityPresenter
import com.jasrdeveloper.gymbilling.view.MainActivity
import com.jasrdeveloper.gymbilling.view.common.components.BaseFragmentOnBackPressed
import javax.inject.Inject

class HomeActivity :
    MainActivity<HomeActivityContract.View, HomeActivityPresenter>(),
    HomeActivityContract.View {
    @Inject
    lateinit var homePresenterActivity: HomeActivityPresenter
    override val context: Context get() = this

    override fun getView(): HomeActivityContract.View = this
    override fun createPresenter(): HomeActivityPresenter = homePresenterActivity
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectorBaseActivity().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homePresenterActivity.onCreateHomeMainFragment()
        homePresenterActivity.load()
        setBackPressed()
    }

    private fun setBackPressed(){
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val fragment = this@HomeActivity.supportFragmentManager.findFragmentById(R.id.actHome_frmLayout)
                (fragment as? BaseFragmentOnBackPressed)?.onBackPressed()?.not()?.let {
                    if (it) {
                        homePresenterActivity.backFragment(false)
                    }
                }
            }
        }
        )
    }


}