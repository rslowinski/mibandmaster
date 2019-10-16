package com.radslow.mibandtools.repository

import com.radslow.mibandtools.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsernameOrEmail(usernameOrEmail: String, email: String): User?

}