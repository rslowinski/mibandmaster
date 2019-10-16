package com.radslow.mibandtools.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(
    name = "users", uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"]),
        UniqueConstraint(columnNames = ["email"])
    ]
)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @NaturalId @NotBlank @Size(max = 60)
    var email: String,

    @NotBlank @Size(max = 100)
    var password: String,

    @NotBlank @Size(max = 60)
    var name: String = "",

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: Set<Role> = emptySet()

) : DateAudit() {

    fun setRole(role: Role) {
        roles = setOf(role)
    }
}


