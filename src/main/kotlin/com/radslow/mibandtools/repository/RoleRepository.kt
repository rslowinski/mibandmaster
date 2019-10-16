package com.radslow.mibandtools.repository

import com.radslow.mibandtools.model.Role
import com.radslow.mibandtools.model.RoleName
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {

    fun getByName(name: RoleName): Role
}