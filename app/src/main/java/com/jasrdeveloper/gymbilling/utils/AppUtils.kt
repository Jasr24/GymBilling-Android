package com.jasrdeveloper.gymbilling.utils

import com.jasrdeveloper.gymbilling.R

class AppUtils {
    companion object {
        fun emailValidation(email: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+"
            return email.matches(regex = Regex(emailPattern))
        }

        fun getPhoneNumber(phoneNumber: String): String {
            return phoneNumber.replace("([\\- ()])".toRegex(), "")
        }

        fun isOnline(): Boolean? {
            try {
                val runTime = Runtime.getRuntime().exec("ping -c 1 www.google.es")
                val value = runTime.waitFor()
                return value == 0
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return false
        }

        fun getMessageCustomError(code: Int): Int {
            return when (code) {
                1, 4 -> R.string.logical_error

                else -> R.string.servidor_error
            }
        }

        fun getTitleCustomError(code: Int): Int {
            return when (code) {
                20 -> R.string.ups_error
                else -> R.string.we_are_sorry
            }
        }
    }
}