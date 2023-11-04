package com.jasrdeveloper.gymbilling.view.common.components.custom_dialog_spinner_load

import android.content.Context

interface CustomDialogSpinnerLoadContract {
    fun create(context: Context): CustomDialogSpinnerLoad
    fun showSpinner()
    fun hideSpinner()
}