package com.jasrdeveloper.gymbilling.view.common.components.custom_dialog_spinner_load

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.jasrdeveloper.gymbilling.R
import com.jasrdeveloper.gymbilling.databinding.CustomDialogSpinnerLoadBinding
import java.lang.Exception

@SuppressLint("InflateParams")
class CustomDialogSpinnerLoad : CustomDialogSpinnerLoadContract {

    private var alertDialog: AlertDialog? = null
    private lateinit var binding: CustomDialogSpinnerLoadBinding

    override fun create(context: Context): CustomDialogSpinnerLoad {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val dialogView: View = inflater.inflate(R.layout.custom_dialog_spinner_load, null)
        binding = CustomDialogSpinnerLoadBinding.bind(dialogView)
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

        Glide.with(context)
            .load(context.getDrawable(R.drawable.spinner))
            .into(binding.cdslIvLoad)


        alertDialog = dialogBuilder.create()
        alertDialog?.setCancelable(false)
        alertDialog?.window?.setBackgroundDrawable(ContextCompat.getDrawable(context,android.R.color.transparent))
        alertDialog?.window?.attributes?.gravity = Gravity.CENTER
        return this
    }

    override fun showSpinner() {
        alertDialog?.show()
    }

    override fun hideSpinner() {
        try {
            alertDialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}