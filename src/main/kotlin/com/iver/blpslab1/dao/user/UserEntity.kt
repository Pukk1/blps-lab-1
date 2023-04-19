package com.iver.blpslab1.dao.user

import com.iver.blpslab1.security.UserDetails
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "user_entity")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val username: String,
    val password: String,
    val isActive: Boolean,
) {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
    )

    @Enumerated(EnumType.STRING)
    lateinit var roles: MutableSet<Role>
}

fun UserEntity.toSecurityModel(): UserDetails = UserDetails(
    username = username,
    password = password,
    authorities = roles.map { GrantedAuthority { it.name } },
    isActive = isActive,
)
