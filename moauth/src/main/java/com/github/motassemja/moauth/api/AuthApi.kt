/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth.api

import com.github.motassemja.moauth.model.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AuthApi {
    @FormUrlEncoded
    @POST("oauth/token")
    fun authenticate(@Field("grant_type") grantType: String): Call<AccessToken>
}