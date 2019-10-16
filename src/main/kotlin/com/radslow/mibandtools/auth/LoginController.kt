package com.radslow.mibandtools.auth

import com.radslow.mibandtools.model.RoleName
import com.radslow.mibandtools.model.User
import com.radslow.mibandtools.repository.RoleRepository
import com.radslow.mibandtools.repository.UserRepository
import com.radslow.mibandtools.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("api/v1/auth")
class LoginController(
    val authManager: AuthenticationManager,
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val passwordEncoder: PasswordEncoder,
    val tokenProvider: JwtTokenProvider
) {

    @PostMapping("/signin")
    fun signIn(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<JwtAuthResponse> {
        val auth = authManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
        )

        SecurityContextHolder.getContext().authentication = auth
        val jwt = tokenProvider.generateToken(auth)
        return ResponseEntity.ok(JwtAuthResponse(jwt))
    }

    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody request: RegisterRequest): ResponseEntity<Any> {
        if (userRepository.existsByEmail(request.email)) {
            return ResponseEntity(HttpStatus.PRECONDITION_FAILED)
        }

        val user = User(email = request.email, password = passwordEncoder.encode(request.password))
        user.setRole(roleRepository.getByName(RoleName.ROLE_USER))
        userRepository.save(user)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }


}

data class LoginRequest(
    @NotBlank
    val email: String,

    @NotBlank
    val password: String
)

data class RegisterRequest(
    @NotBlank @Size(min = 3, max = 60)
    val email: String,

    @NotBlank @Size(min = 6, max = 100)
    val password: String
)
