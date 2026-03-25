package com.sd.laborator.auth.pojo

data class CryptoResponse(
    var success: Boolean = false,
    var message: String = "",
    var encryptedText: String? = null
)