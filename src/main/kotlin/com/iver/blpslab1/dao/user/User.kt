//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//
//
//class User : UserDetails {
//    private val id: Long? = null
//    private val username: String? = null
//    private val password: String? = null
//    private val roles: List<Role>? = null
//
//    // getters and setters for all fields
//    override fun getAuthorities(): Collection<GrantedAuthority> {
//        val authorities: MutableList<GrantedAuthority> = ArrayList()
//        for (role in roles) {
//            authorities.add(SimpleGrantedAuthority(role.getName()))
//        }
//        return authorities
//    }
//
//    override fun getPassword(): String {
//        return password!!
//    }
//
//    override fun getUsername(): String {
//        return username!!
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        return true
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isEnabled(): Boolean {
//        return true
//    }
//}