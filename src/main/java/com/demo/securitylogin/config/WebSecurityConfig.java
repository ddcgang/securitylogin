package com.demo.securitylogin.config;

import com.demo.securitylogin.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨站请求伪造
        http.csrf().disable();
        //iframe error[in a frame because it set 'X-Frame-Options' to 'deny'.]
        http.headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
/*//鉴权
        http.addFilterBefore(new MyVerifyFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().
                antMatchers("/login/**").permitAll().
                anyRequest().authenticated().
                and().formLogin().loginPage("/login/index")
                .loginProcessingUrl("/login/check")
                .successHandler(new MyAuthenticationSuccessHandler())
                .failureHandler(new MyAuthenticationFailureHandler())
                .and().logout().permitAll();*/

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder() {
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return super.encode(rawPassword);
                    }

                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        //此处个性化验证加密方式
                        if (!BCrypt.checkpw(rawPassword.toString(), encodedPassword))
                            throw new BadCredentialsException("账号和密码不匹配");
                        return true;
                    }
                });
    }

    @Override
    public void configure(WebSecurity web) {
        //放行静态资源
        web.ignoring().antMatchers("/js/**"
                , "/css/**"
                , "/img/**"
                , "/html/**"
                , "/fonts/**"
                , "/upload/**"
        );
    }

}
