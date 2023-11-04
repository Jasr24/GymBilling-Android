package com.jasrdeveloper.gymbilling.utils

import android.util.Log
import com.google.gson.Gson
import com.jasrdeveloper.gymbilling.repository.networking.api.rest_model.response.GenericResponse
import com.jasrdeveloper.gymbilling.utils.globals.GlobalsVar
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.nio.charset.Charset



class NetworkingInterceptor(
    private val type: Int = 1
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        if (response.isSuccessful) {
            val newResponse = response.newBuilder()
            var bodyDecrypt: String
            var bodyEncrypt: String = ""
            try {
                val bodyEncryptResult = String(response.body()!!.bytes(), Charset.forName("UTF-8"))
                bodyEncrypt = bodyEncryptResult
                var typeCypher = EncryptHelper.CIPHERS.DEVELOPER_AES

                bodyDecrypt = EncryptHelper().decrypt(bodyEncryptResult, typeCypher)
            } catch (e: Exception) {
                Log.e(GlobalsVar.GlobalsVar_logTag, "${e.message}")
                val objBaseError: GenericResponse<String> = GenericResponse()
                objBaseError.error = true
                objBaseError.success = false
                objBaseError.message = "No fue posible decodificar la respuesta. => ($bodyEncrypt)"
                bodyDecrypt = Gson().toJson(objBaseError)
            }
            newResponse.body(
                ResponseBody.create(
                    MediaType.parse("text/plain"),
                    bodyDecrypt
                )
            )
            return newResponse.build()
        }
        return response
    }
}
