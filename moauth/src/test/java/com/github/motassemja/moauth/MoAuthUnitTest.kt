/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.motassemja.moauth.model.AccessTokenType
import com.github.motassemja.moauth.model.MoAuthConfig
import com.github.motassemja.moauth.model.credentias.ClientCredentials
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.rules.TestRule


/**
 *
 */
class MoAuthUnitTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    val server = MockWebServer()

    val gson = GsonBuilder().create()

    @Before
    fun setUp() {
        server.start()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun fetchNewToken() {
        //Given
        val response = MockResponse()
        val mockAccessToken = MockAccessToken("1234-1234-1234", AccessTokenType.BEARER)
        val json = gson.toJson(mockAccessToken)
        response.status = "200"
        response.setResponseCode(200)
        response.setBody(json)

        //When
        server.enqueue(response)
        val url = server.url("/")

        val config = MoAuthConfig(url.toString(), ClientCredentials("clientId", "clientSecret"))
        val clientBuilder = OkHttpClient.Builder()

        val repo = MoAuthRepository(config, clientBuilder)
        repo.getValidTokenExecute()

        Assert.assertEquals(mockAccessToken, repo.getValidToken().value)
    }
}