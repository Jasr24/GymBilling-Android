package com.jasrdeveloper.gymbilling.view.common.base_fragment

interface BaseFragmentCallback  {
    fun <T>onFragmentSuccess(actionCode : Int, data: T? = null)
    fun onFragmentFailure()
}