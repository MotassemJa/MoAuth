/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth.service

import com.github.motassemja.moauth.api.AuthApi
import com.github.motassemja.moauth.interceptors.AuthorizationInterceptor
import com.github.motassemja.moauth.model.MoAuthConfig
import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object MoAuthService {

    fun createService(config: MoAuthConfig, okHttpClient: OkHttpClient.Builder): AuthApi {
        val gson = GsonBuilder().serializeNulls().create()

        // Avoid retrofit exception
        var url = config.tokenUrl;
        if (!config.tokenUrl.endsWith("/")) {
            url += "/"
        }

        val httpClient = okHttpClient.addInterceptor(
            AuthorizationInterceptor(
                Credentials.basic(
                    config.credentials.clientID,
                    config.credentials.clientSecret
                )
            )
        ).build()

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
            .create(AuthApi::class.java)
    }

}
