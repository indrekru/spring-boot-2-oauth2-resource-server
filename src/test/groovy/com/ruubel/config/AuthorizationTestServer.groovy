package com.ruubel.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.test.context.ActiveProfiles

@Configuration
@EnableAuthorizationServer
@ActiveProfiles("test")
class AuthorizationTestServer extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager

    @Autowired
    AuthorizationTestServer(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager
    }

    @Override
    void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("permitAll()")
        oauthServer.allowFormAuthenticationForClients()
    }

    @Override
    void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients)
        clients.inMemory()
                .withClient("user")
                .secret(passwordEncoder().encode("password"))
                .authorizedGrantTypes("password")
                .scopes("ledger")
    }

    @Override
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints)
        endpoints.authenticationManager(this.authenticationManager)
    }

    @Bean
    static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}
