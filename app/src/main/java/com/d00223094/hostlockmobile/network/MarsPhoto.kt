package com.d00223094.hostlockmobile.network

import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val file: String,
    val license: String? = null,
    val owner: String? = null
)