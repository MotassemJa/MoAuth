/*
 * Copyright (c) 2019 T-Systems
 * All rights reserved.
 * Created by MOJA 
 * Date: 25.07.2019
 */
package com.github.motassemja.moauth.model

import com.github.motassemja.moauth.model.credentias.ClientCredentials


/**
 *
 */
data class MoAuthConfig(val tokenUrl: String, val credentials: ClientCredentials)