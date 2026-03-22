package com.sd.laborator.crypto.services

import com.sd.laborator.crypto.interfaces.InterfaceCrypto
import com.sd.laborator.crypto.pojo.CryptoData
import org.springframework.stereotype.Service
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Service
class CryptoService: InterfaceCrypto {

    private val secretKey = "LAB_SD_04_SECRET"
    private val initVector = "LAB_SD_04_IV_123"

    override fun encrypt(text: String): CryptoData {
        return try {
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), "AES")
            val ivSpec = IvParameterSpec(initVector.toByteArray())
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec)

            val encryptedText = Base64.getEncoder().encodeToString(
                cipher.doFinal(text.toByteArray())
            )
            CryptoData(text, encryptedText)
        } catch (e: Exception) {
            CryptoData(text, "")
        }
    }
}