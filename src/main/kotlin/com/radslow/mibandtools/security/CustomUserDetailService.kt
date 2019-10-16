package com.radslow.mibandtools.security

import com.radslow.mibandtools.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with email : $email")

        return UserPrincipal.create(user)
    }

    fun loadUserById(id: Long): UserDetails {
        val user = userRepository.findById(id).orElseThrow { throw IllegalStateException() }

        return UserPrincipal.create(user)
    }
}