/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth.model


/**
 *
 */
abstract class AccessToken {

    /**
     * Returns a string containing token type and value
     * E.g. 'BEARER 1234-1234-1234-1234
     */
    abstract fun getAccessToken(): String

    /**
     * To be called to check if current token is still valid
     */
    abstract fun isTokenValid(): Boolean
}