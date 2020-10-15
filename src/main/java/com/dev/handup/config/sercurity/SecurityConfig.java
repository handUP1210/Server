package com.dev.handup.config.sercurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    // 웹
    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    // Http
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // URL 구분, 권한 설정
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/lib/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .anyRequest().permitAll()
                //.anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/");
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
    }
}
