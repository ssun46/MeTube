package com.dev.metube.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dev.metube.service.UserLoginDetailsService;
import com.dev.metube.util.PasswordEncode;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserLoginDetailsService userLoginDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncode().passwordEncoder("SHA-256");
	};
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		// 페이지 권한 설정
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
            .and() // 로그인 설정
        		.formLogin()
                .loginPage("/signin")
                .defaultSuccessUrl("/")
                .permitAll();
//            .and() // 로그아웃 설정
//            	.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//                .logoutSuccessUrl("/user/logout/result")
//                .invalidateHttpSession(true)
//            .and()
//                // 403 예외처리 핸들링
//            	.exceptionHandling().accessDeniedPage("/user/denied");
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// add our Users for in memory authentication
        // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    	auth.userDetailsService(userLoginDetailsService)
    		.passwordEncoder(passwordEncoder());
    }
}
