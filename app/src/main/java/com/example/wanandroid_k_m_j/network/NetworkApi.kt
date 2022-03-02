package com.example.wanandroid_k_m_j.network

import com.google.gson.GsonBuilder
import com.huastart.vpn.utils.log
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author TangRui
 * @description:
 * @date:2022/2/23 17:31
 */
object NetworkApi {
    fun getApi(): NetApiService {
        return Retrofit.Builder().baseUrl(Urls.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(getOkHttpClient()).build().create(NetApiService::class.java)
    }

    /**
     * 配置http
     */
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(getInterceptor())
        }.build()
    }

    /**
     * 配置拦截器
     */
    private fun getInterceptor(): Interceptor {
        val rLog = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                message.log()
                Logger.i(message)
            }
        }
        return HttpLoggingInterceptor(rLog).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}