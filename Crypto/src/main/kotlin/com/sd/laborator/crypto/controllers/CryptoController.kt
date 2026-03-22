package com.sd.laborator.crypto.controllers

import com.sd.laborator.crypto.interfaces.InterfaceCrypto
import com.sd.laborator.crypto.pojo.CryptoData
import com.sd.laborator.crypto.services.CryptoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/crypto")
class CryptoController {
    @Autowired
    private lateinit var cryptoService: InterfaceCrypto

    @RequestMapping("/encrypt", method = [RequestMethod.POST])
    fun encrypt(@RequestBody data: CryptoData): ResponseEntity<CryptoData> {
        val cryptoData = cryptoService.encrypt(data.text)
        val status = if (cryptoData.encryptedText == null) {
           HttpStatus.INTERNAL_SERVER_ERROR
        }
        else
            HttpStatus.OK
        return ResponseEntity(cryptoData, status)
    }
}