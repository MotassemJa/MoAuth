/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth.model

import com.google.gson.annotations.SerializedName


enum class AccessTokenType {

    @SerializedName("Bearer")
    BEARER,

    @SerializedName("Basic")
    BASIC
}