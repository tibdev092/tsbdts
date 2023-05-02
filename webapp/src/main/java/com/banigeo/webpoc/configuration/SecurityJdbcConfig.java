package com.banigeo.webpoc.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Profile("mysql")
public class SecurityJdbcConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("""
                                select username,password,enabled 
                                from users 
                                where username=?""")
                .authoritiesByUsernameQuery("""
                        select u.username, a.role as authority 
                        from users u inner join users_authorities ua on (u.user_id = ua.user_id) 
                        inner join authorities a  on (a.role_id = ua.role_id) where u.username=?
                        """);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .antMatchers("/emp/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/job/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/department/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/location/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/region/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/country/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/emp/history/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/emp/modificationForm/*").hasRole("ADMIN")
                .antMatchers("/emp/registerForm").hasRole("ADMIN")
                .antMatchers("/emp/create").hasRole("ADMIN")
                .antMatchers("/emp/modify").hasRole("ADMIN")
                .antMatchers("/job/registerForm").hasRole("ADMIN")
                .antMatchers("/job/create").hasRole("ADMIN")
                .antMatchers("/country/register").hasRole("ADMIN")
                .antMatchers("/country/create").hasRole("ADMIN")
                .antMatchers("/region/register").hasRole("ADMIN")
                .antMatchers("/region/create").hasRole("ADMIN")
                .antMatchers("/location/register").hasRole("ADMIN")
                .antMatchers("/location/create").hasRole("ADMIN")
                .antMatchers("/department/register").hasRole("ADMIN")
                .antMatchers("/department/create").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/showLogInForm")
                .loginProcessingUrl("/authUser")
                .failureUrl("/login-error").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
