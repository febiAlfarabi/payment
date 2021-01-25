package com.numeralasia.payment.config;

import com.numeralasia.payment.security.JwtConfigurer;
import com.numeralasia.payment.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.github.febialfarabi.utility.MSRestTemplate;

@Configuration
//@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


//    @Autowired JwtTokenProvider jwtTokenProvider;
    @Autowired PasswordEncoder passwordEncoder ;

    @Value("${swagger-username}")
    private String swaggerUsername;

    @Value("${swagger-password}")
    private String swaggerPassword;
    @Value("${secure.swagger}")
    private boolean secureSwagger;



    @Autowired
    MSRestTemplate restTemplate ;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api**").permitAll()
                .antMatchers("/swagger-ui.html**")
                .authenticated().and().formLogin().and().apply(new JwtConfigurer());
    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder.encode(swaggerPassword);
        auth.inMemoryAuthentication().withUser(swaggerUsername).password(password).authorities(Constant.SWAGGER);
    }



}
