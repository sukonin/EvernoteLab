package com.epam.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserAuthenticationProvider userAuthenticationProvider;

  public SecurityConfiguration(UserAuthenticationProvider userAuthenticationProvider) {
    this.userAuthenticationProvider = userAuthenticationProvider;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.httpBasic();


    http.authorizeRequests()
        .antMatchers("/login/**")
        .access("hasRole('ROLE_USER')")
        .and()
        .formLogin()
        .defaultSuccessUrl("/", false);

    http.authorizeRequests()
        .antMatchers("/registration")
        .permitAll();

    http.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll();

    //Private
    http.authorizeRequests().antMatchers("/notebooks/**").authenticated();
    http.authorizeRequests().antMatchers("/users/**").authenticated();
    http.authorizeRequests().antMatchers("/notes/**").authenticated();
    http.authorizeRequests().antMatchers("/tags/**").authenticated();

    // Logout
    http.logout().logoutUrl("/logout");
    http.logout().logoutSuccessUrl("/");

    http.formLogin().defaultSuccessUrl("/success");

    http.logout()
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");

    http.headers().frameOptions().disable();//make console available

    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.authenticationProvider(userAuthenticationProvider);
  }


}
