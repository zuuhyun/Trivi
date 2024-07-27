package web.trivi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
//                .requestMatchers("/security-login/info").authenticated()
                .anyRequest()
                .permitAll()
//                .and()
//                .formLogin()
//                .usernameParameter("loginId")
//                .passwordParameter("password")
//                .loginPage("/security-login/login")
//                .defaultSuccessUrl("/security-login")
//                .failureUrl("/security-login/login")
//                .and()
//                .logout(logout ->
//                        logout
//                                .logoutUrl("api/v1/users/logout")
//                                .invalidateHttpSession(true)
//                                .deleteCookies("JSESSIONID")
//                                .logoutSuccessUrl("/api/v1/users/login?logout")
//                )
        ;

        return http.build();
    }


}

