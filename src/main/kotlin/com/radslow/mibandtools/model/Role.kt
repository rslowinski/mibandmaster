package com.radslow.mibandtools.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    var name: RoleName? = null

}

enum class RoleName {
    ROLE_USER,
    ROLE_ADMIN
}