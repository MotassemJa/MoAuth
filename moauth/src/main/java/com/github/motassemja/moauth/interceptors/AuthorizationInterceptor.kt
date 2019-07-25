/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth.interceptors

import okhttp3.Interceptor
import okhttp3.Response


/**
 *
 */
class AuthorizationInterceptor(val authorization: String) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val build = chain.request()
            .newBuilder()
            .addHeader("Authorization", authorization)
            .build()

        return chain.proceed(build)
    }
}