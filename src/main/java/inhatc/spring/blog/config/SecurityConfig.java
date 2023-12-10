package inhatc.spring.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import inhatc.spring.blog.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter wAuthenticationFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
            // .formLogin(Customizer.withDefaults())
            // .formLogin(login -> login
            //                     .loginPage("/member/login")
            //                     .permitAll()
            //                     )   
            // .logout(Customizer.withDefaults());

        http
            .authorizeHttpRequests(request -> request
                                    .requestMatchers("/**").permitAll() // 모든 경로 인증 하지 않음.
                                    .requestMatchers(HttpMethod.POST, "/member/new").permitAll()
                                    // .requestMatchers(HttpMethod.GET, "/board/**").permitAll()
                                    .anyRequest().authenticated());

        // 각 페이지에 대한 접근 권한 설정
        // http.authorizeHttpRequests(request -> request
        //         .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
        //         .requestMatchers("/", "/member/**", "/user/**", "/login/**").permitAll()
        //         .anyRequest().authenticated());

        // http.exceptionHandling(exception -> exception
        //         .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        http.addFilterBefore(wAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // http.formLogin(form -> form
        //                 .usernameParameter("email")
        //                 .passwordParameter("password")
        //                 .permitAll()
        //                 );

        // http.logout(Customizer.withDefaults());

        return http.build();
    }
}
