/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth

import com.github.motassemja.moauth.model.AccessToken
import com.github.motassemja.moauth.model.AccessTokenType
import com.google.gson.annotations.SerializedName


/**
 *
 */
data class MockAccessToken(
    @SerializedName("access_token")
    val token: String,

    @SerializedName("token_type")
    val type: AccessTokenType
) : AccessToken() {

    override fun getAccessToken(): String {
        return "${type.name} $token"
    }

    override fun isTokenValid(): Boolean {
        return true
    }
}