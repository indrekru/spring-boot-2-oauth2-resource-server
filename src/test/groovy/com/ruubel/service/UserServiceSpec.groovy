package com.ruubel.service

import com.ruubel.model.Role
import com.ruubel.model.User
import com.ruubel.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class UserServiceSpec extends Specification {

    UserService userService
    UserRepository userRepository
    RoleService roleService
    PasswordEncoder passwordEncoder

    def setup () {
        roleService = Mock(RoleService)
        userRepository = Mock(UserRepository)
        passwordEncoder = Mock(PasswordEncoder)

        userService = new UserService(userRepository, passwordEncoder, roleService)
    }

    def "when user exists, then returns null" () {
        given:
            String email = "wat@wot.com"
            User user = Mock(User)
        when:
            User createdUser = userService.createUser(email, "password")
        then:
            createdUser == null
            1 * userRepository.findByEmail(email) >> user
            0 * userRepository.save(_)
    }

    def "when user doesn't exist, then creates user and returns user" () {
        given:
            String email = "wat@wot.com"
            String password = "password"
            String hashedPassword = "hashedPassword"
            Role role = new Role(UserService.ROLE_USER)
        when:
            User createdUser = userService.createUser(email, password)
        then:
            createdUser.email == email
            createdUser.password == hashedPassword
            1 * userRepository.findByEmail(email) >> null
            1 * passwordEncoder.encode(password) >> hashedPassword
            1 * roleService.findByRole(UserService.ROLE_USER) >> role
            0 * roleService.saveRole(UserService.ROLE_USER)
            1 * userRepository.save(_)
    }

    def "when user doesn't exist and role doesn't exist, then creates user, role and returns user" () {
        given:
            String email = "wat@wot.com"
            String password = "password"
            String hashedPassword = "hashedPassword"
            Role role = null
        when:
            User createdUser = userService.createUser(email, password)
        then:
            createdUser.email == email
            createdUser.password == hashedPassword
            1 * userRepository.findByEmail(email) >> null
            1 * passwordEncoder.encode(password) >> hashedPassword
            1 * roleService.findByRole(UserService.ROLE_USER) >> role
            1 * roleService.saveRole(UserService.ROLE_USER) >> new Role(UserService.ROLE_USER)
            1 * userRepository.save(_)
    }

}
