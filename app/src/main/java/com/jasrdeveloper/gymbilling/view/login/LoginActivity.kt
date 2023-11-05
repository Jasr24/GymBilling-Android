package com.jasrdeveloper.gymbilling.view.login


import android.content.Context
import android.os.Bundle
import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.contract.login.LoginActivityContract
import com.jasrdeveloper.gymbilling.databinding.ActivityLoginBinding
import com.jasrdeveloper.gymbilling.presenter.login.LoginActivityPresenter
import com.jasrdeveloper.gymbilling.view.MainActivity
import com.jasrdeveloper.gymbilling.view.common.components.BaseFragmentOnBackPressed
import javax.inject.Inject

class LoginActivity() : MainActivity<LoginActivityContract.View, LoginActivityPresenter>(),
    LoginActivityContract.View{
    @Inject
    lateinit var loginPresenterActivity: LoginActivityPresenter
    override val context: Context get() = this
    override fun getView(): LoginActivityContract.View = this
    override fun createPresenter(): LoginActivityPresenter = loginPresenterActivity
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectorBaseActivity().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginPresenterActivity.onCreateLoginMainFragment()
        loginPresenterActivity.load()
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.actLogin_frmLayout)
        (fragment as? BaseFragmentOnBackPressed)?.onBackPressed()?.not()?.let {
            if (it) {
                loginPresenterActivity.backFragment(false)
            }
        }
    }
}