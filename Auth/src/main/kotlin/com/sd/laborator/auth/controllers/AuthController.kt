package com.sd.laborator.auth.controllers

import com.sd.laborator.auth.pojo.LoginData
import com.sd.laborator.auth.pojo.RegisterData
import com.sd.laborator.auth.services.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterData) : String{
        return authService.register(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginData) : String{
        return authService.login(request)
    }
}