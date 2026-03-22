package com.sd.laborator.crypto.interfaces

import com.sd.laborator.crypto.pojo.CryptoData

interface InterfaceCrypto {
    fun encrypt(text: String): CryptoData
}