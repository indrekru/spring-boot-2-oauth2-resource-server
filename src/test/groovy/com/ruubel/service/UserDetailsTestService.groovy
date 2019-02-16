package com.ruubel.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.test.context.ActiveProfiles

@Service
@ActiveProfiles("test")
class UserDetailsTestService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new User("dummyUser", passwordEncoder.encode("dummyPassword"), true, true, true, true,
                AuthorityUtils.createAuthorityList("ROLE_USER"))
    }
}
