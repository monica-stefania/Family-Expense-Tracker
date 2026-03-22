package com.sd.laborator.auth.services

import com.sd.laborator.auth.interfaces.MemberRepository
import com.sd.laborator.auth.pojo.LoginData
import com.sd.laborator.auth.pojo.Member
import com.sd.laborator.auth.pojo.RegisterData
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val memberRepository: MemberRepository,
) {
    fun register(data: RegisterData): String {
        if(memberRepository.existsByUsername(data.username)){
            return "Username already exists"
        }

        val member = Member(username = data.username, password = data.password, firstName =  data.firstName, lastName =  data.lastName)
        memberRepository.save(member)
        return "User created with success!"
    }
    fun login(data: LoginData): String {

        if(!memberRepository.existsByUsername(data.username)){
            return "Username not exists! Please create a new user."
        }

        val member = memberRepository.findByUsername(data.username)

        if(member != null) {
            if (member.password != data.password) {
                return "Password do not match!"
            } else
                return "Login successful"
        }

        return "Invalid username or password!"
    }
}