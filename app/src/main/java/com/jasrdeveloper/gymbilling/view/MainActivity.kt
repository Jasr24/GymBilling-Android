package com.jasrdeveloper.gymbilling.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.jasrdeveloper.gymbilling.databinding.ActivityMainBinding
import com.jasrdeveloper.gymbilling.presenter.common.BasePresenter
import com.jasrdeveloper.gymbilling.view.common.components.BaseActivity
import com.jasrdeveloper.gymbilling.view.common.components.BaseView
import com.jasrdeveloper.gymbilling.view.common.components.ContainerViewConfigProperties
import com.jasrdeveloper.gymbilling.view.common.components.custom_dialog_spinner_load.CustomDialogSpinnerLoad


abstract class MainActivity<V : BaseView, P : BasePresenter<V>> : BaseActivity<V, P>() {

    lateinit var parentActivityContext: Context
    private var mainContainer: DrawerLayout? = null
    lateinit var spinnerDialog: CustomDialogSpinnerLoad
    private lateinit var containerViewConfig: ContainerViewConfigProperties
    private lateinit var binding : ActivityMainBinding
    private var me: Activity? = null
    private var view: View? = null

    override fun containerViewConfig(containerViewConfig: ContainerViewConfigProperties) {
        this.containerViewConfig = containerViewConfig
        binding.acmMainHeader.visibility = View.VISIBLE
        binding.actMFrmMain.visibility = View.VISIBLE

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        spinnerDialog = CustomDialogSpinnerLoad().create(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("InflateParams")
    override fun setContentView(view: View?)  {
        mainContainer = binding.root
        val activityContainer = binding.actMFrmMain
        activityContainer.addView(view)
        super.setContentView(mainContainer)
        me = this
        listeners()
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        view = parent
        return super.onCreateView(parent, name, context, attrs)
    }

    private fun listeners(){
        binding.flmImgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun goActivity(id: Int): Boolean{
        return toFlow(id)
    }

    private fun toFlow(activityCode: Int):Boolean{
        val redirectActivity = when(activityCode){

            else -> null
        }
        redirectActivity?.let {
            startActivity(Intent(this, it).apply {
                flags =Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            )
            return true
        }?:run{
            return false
        }
    }

}