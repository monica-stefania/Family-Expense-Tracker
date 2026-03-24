package com.sd.laborator.auth.services

import com.sd.laborator.auth.interfaces.InterfaceAuth
import com.sd.laborator.auth.interfaces.MemberRepository
import com.sd.laborator.auth.pojo.AuthResponse
import com.sd.laborator.auth.pojo.LoginData
import com.sd.laborator.auth.pojo.Member
import com.sd.laborator.auth.pojo.RegisterData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService: InterfaceAuth
{
    @Autowired
    private lateinit var memberRepository: MemberRepository

    override fun register(data: RegisterData): AuthResponse {
        if(memberRepository.existsByUsername(data.username)){
            return AuthResponse(false, "Username already exists")
        }

        val member = Member(username = data.username, password = data.password, firstName =  data.firstName, lastName =  data.lastName)
        val newMember = memberRepository.save(member)
        return AuthResponse(true, "User created with success!", newMember.id, newMember.username)
    }
    override fun login(data: LoginData): AuthResponse {

        if(!memberRepository.existsByUsername(data.username)){
            return AuthResponse(false, "Username not exists! Please create a new user.")
        }

        val member = memberRepository.findByUsername(data.username)

        if(member != null) {
            if (member.password != data.password) {
                return AuthResponse(false, "Password do not match!")
            } else
                return AuthResponse(true, "Login successful", member.id, member.username)
        }

        return AuthResponse(false,"Invalid username or password!")
    }
}