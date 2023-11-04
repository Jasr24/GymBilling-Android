package com.jasrdeveloper.gymbilling.di.modules

import android.annotation.SuppressLint
import com.jasrdeveloper.gymbilling.common.BaseApplication
import com.jasrdeveloper.gymbilling.utils.NetworkingInterceptor
import com.jasrdeveloper.gymbilling.utils.globals.GlobalsModulesName
import com.jasrdeveloper.gymbilling.utils.globals.GlobalsVar
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CommonModules(val context: BaseApplication) {

    @Singleton
    @Provides
    @Named(GlobalsModulesName.MODULE_NAME_restApi)
    fun retrofitRest(): Retrofit{
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @SuppressLint("")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

                @SuppressLint("")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        val logging = HttpLoggingInterceptor()
        if(GlobalsVar.GlobalsVar_showLogsInterceptor){
            logging.level = HttpLoggingInterceptor.Level.BODY
        }

        val builder = OkHttpClient.Builder()
        if (GlobalsVar.GlobalsVar_debugAppSSL) {
            builder.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            builder.hostnameVerifier { _, _ -> true }
        }
        builder.readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
        if (GlobalsVar.GlobalsVar_debugAppHTTP) {
            builder.addInterceptor(logging)
        }

        builder.addNetworkInterceptor(NetworkingInterceptor())

        return Retrofit.Builder()
            .baseUrl(GlobalsVar.GlobalsVar_baseUrlCustomerREST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build()).build()
    }
}