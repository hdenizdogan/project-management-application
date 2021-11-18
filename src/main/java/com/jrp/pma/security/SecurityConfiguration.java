package com.jrp.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled "+
                        "from user_accounts where username=?")
                .authoritiesByUsernameQuery("select username, role "+
                "from user_accounts where username = ?")
                .dataSource(dataSource)
                .passwordEncoder(encoder);
    }

    /*@Bean
    public PasswordEncoder getPasswordEncoder(){
        //fool the system to show that we have used a PasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/projects/new").hasAuthority("ADMIN")
                .antMatchers("/projects/save").hasAuthority("ADMIN")
                .antMatchers("/employees/new").hasAuthority("ADMIN")
                .antMatchers("/employees/save").hasAuthority("ADMIN")
                .antMatchers("/","/**").permitAll()
                .and().csrf()
                .ignoringAntMatchers( "/app-api/**")
                .ignoringAntMatchers( "/h2-console")
                .and()
                .formLogin();
                // with .hasAuthority("ADMIN") needs to be "ROLE_ADMIN" in the database
    }
}
