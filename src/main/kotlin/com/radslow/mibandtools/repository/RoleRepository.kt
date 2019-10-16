package com.radslow.mibandtools.repository

import com.radslow.mibandtools.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>