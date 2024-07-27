package com.example.lab7;

import com.example.lab7.bean.Account;
import com.example.lab7.dao.AccountsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)// khai báo để @PreAuthorize mới có thể sử dụng
public class AuthConfig implements UserDetailsService {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    AccountsDao accountsDao;

    //demo 7.4
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountsDao.findById(username).get();
            //tao userDetail tu Account
            String password = account.getPassword();
            String[] roles = account.getAuthotirited().stream()
                    .map(au -> au.getRole().getId())
                    .collect(Collectors.toList()).toArray(new String[0]);
            System.out.println("user: " + username);
            System.out.println("user: " + password);
            return User.withUsername(username)
                    .password(getPasswordEncoder().encode(password))
                    .roles(roles).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		CSRF, CORS
        Customizer<CsrfConfigurer<HttpSecurity>> customCSRF = new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CsrfConfigurer<HttpSecurity> t) {
                t.disable();
            }
        };
        Customizer<CorsConfigurer<HttpSecurity>> customCORS = new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> t) {
                t.disable();
            }
        };
        http.csrf(customCSRF).cors(customCORS);

//		Phân quyền sử dụng
        http

                .authorizeHttpRequests(authorizeHttpRequests ->
                                authorizeHttpRequests
                                        //demo 6.4
//                                .requestMatchers("/home/index", "/auth/login/**").permitAll()
//                                .anyRequest().authenticated()

                                        //demo 6.5
//                                        .requestMatchers("/home/admins").hasRole("ADMIN")
//                                        .requestMatchers("/home/users").hasAnyRole("ADMIN", "USER")
//                                        .requestMatchers("/home/authenticated").authenticated()
                                        .anyRequest().permitAll()
                );
        //Điều khiển lỗi truy cập không đúng vai trò
        http
                .exceptionHandling(accessDeniedPage ->
                        accessDeniedPage
                                .accessDeniedPage("/auth/access/denied")
                );


//		Giao diện đăng nhập
        Customizer<FormLoginConfigurer<HttpSecurity>> customLogin = new Customizer<FormLoginConfigurer<HttpSecurity>>() {
            @Override
            public void customize(FormLoginConfigurer<HttpSecurity> t) {
                t.loginPage("/auth/login/form")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/auth/login/success", false)
                        .failureUrl("/auth/login/error")
                        .usernameParameter("username")
                        .passwordParameter("password");
            }
        };
        http.formLogin(customLogin);

//		Ghi nhớ tài khoản
        Customizer<RememberMeConfigurer<HttpSecurity>> customRemember = new Customizer<RememberMeConfigurer<HttpSecurity>>() {
            @Override
            public void customize(RememberMeConfigurer<HttpSecurity> t) {
                t.rememberMeParameter("rememer");
            }
        };
        http.rememberMe(customRemember);

//		Đăng xuất
        Customizer<LogoutConfigurer<HttpSecurity>> customLogout = new Customizer<LogoutConfigurer<HttpSecurity>>() {
            @Override
            public void customize(LogoutConfigurer<HttpSecurity> t) {
                t.logoutUrl("/auth/loggout")
                        .logoutSuccessUrl("/auth/loggout/success");
            }
        };
        http.logout(customLogout);

        return http.build();
    }


}
