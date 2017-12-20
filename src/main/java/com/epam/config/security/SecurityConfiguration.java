package com.epam.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserAuthenticationProvider userAuthenticationProvider;

  @Autowired
  public SecurityConfiguration(UserAuthenticationProvider userAuthenticationProvider) {
    this.userAuthenticationProvider = userAuthenticationProvider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.httpBasic();

    http.authorizeRequests()
        .antMatchers("/registration").anonymous();

    //Private
    http.authorizeRequests().antMatchers("/notebooks/**").authenticated();
    http.authorizeRequests().antMatchers("/users/**").authenticated();
    http.authorizeRequests().antMatchers("/notes/**").authenticated();
    http.authorizeRequests().antMatchers("/tags/**").authenticated();

/*
    // Logout
    http.logout().logoutUrl("/logout");
    http.logout().logoutSuccessUrl("/");
    // Login form
    http.formLogin().loginPage("/login");
    http.formLogin().usernameParameter("username");
    http.formLogin().passwordParameter("password");
    //
    http.formLogin().defaultSuccessUrl("/success");
    http.formLogin().failureUrl("/loginerror");
*/


  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.authenticationProvider(userAuthenticationProvider);
  }

 /* @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }*/


}
