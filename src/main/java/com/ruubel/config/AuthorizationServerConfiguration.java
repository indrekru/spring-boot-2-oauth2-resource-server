package com.ruubel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@Profile("default")
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private DataSource dataSource;

    @Autowired
    public AuthorizationServerConfiguration(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, DataSource dataSource) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) {
        configurer.tokenStore(tokenStore());
        configurer.authenticationManager(authenticationManager);
        configurer.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * Enables calling of /oauth/check_token to all, remove to disable
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.checkTokenAccess("permitAll()");
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Enables the new {prefix} crypto algorithm password hash storing.
     * Default is BCrypt, all passwords are stored as "{bcrypt}hash" in DB
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
