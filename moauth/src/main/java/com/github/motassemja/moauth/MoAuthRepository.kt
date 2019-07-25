/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth

import androidx.lifecycle.MutableLiveData
import com.github.motassemja.moauth.api.AuthApi
import com.github.motassemja.moauth.model.AccessToken
import com.github.motassemja.moauth.model.MoAuthConfig
import com.github.motassemja.moauth.service.MoAuthService
import okhttp3.OkHttpClient
import retrofit2.Response


/**
 *
 */
class MoAuthRepository(config: MoAuthConfig, okHttpBuilder: OkHttpClient.Builder) {

    private val authApi: AuthApi = MoAuthService.createService(config, okHttpBuilder)

    private val currentToken: MutableLiveData<AccessToken> by lazy {
        MutableLiveData<AccessToken>()
    }

    fun getValidToken(): MutableLiveData<AccessToken> {
        if (currentToken.value == null || !currentToken.value?.isTokenValid()!!) {
            authApi.authenticate("client_credentials")
                .enqueue(object : retrofit2.Callback<AccessToken> {
                    override fun onFailure(call: retrofit2.Call<AccessToken>, t: Throwable) {
                        currentToken.value = null
                    }

                    override fun onResponse(call: retrofit2.Call<AccessToken>, response: Response<AccessToken>) {
                        if (response.isSuccessful) {
                            currentToken.value = response.body()
                        } else {
                            currentToken.value = null
                        }
                    }

                })
        }
        return currentToken
    }

    fun getValidTokenExecute() : MutableLiveData<AccessToken> {
        if (currentToken.value == null || !currentToken.value?.isTokenValid()!!) {
            val execute = authApi.authenticate("client_credentials").execute()
            if (execute.isSuccessful) {
                currentToken.value = execute.body()
            } else {
                currentToken.value = null
            }
        }
        return currentToken
    }
}