package com.khk.backjoonrecommender.config;

import com.khk.backjoonrecommender.config.jwt.JwtAuthenticationFilter;
import com.khk.backjoonrecommender.config.jwt.JwtAuthorizationFilter;
import com.khk.backjoonrecommender.config.jwt.JwtProperties;
import com.khk.backjoonrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtProperties jwtProperties;
	private final UserRepository userRepository;
	private final CorsFilter corsFilter;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter =
				new JwtAuthenticationFilter(jwtProperties, authenticationManager());
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		return jwtAuthenticationFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilter(corsFilter)
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.formLogin().disable()
				.httpBasic().disable()
				.addFilter(jwtAuthenticationFilter())
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository, jwtProperties))
				.authorizeRequests()
				// login api
				.antMatchers("/login").permitAll()
				.antMatchers("/members/sign-up").permitAll() // sign-up
				.anyRequest().authenticated();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/user/**", "/");
		web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/user");
		web.ignoring().antMatchers("/api/v1/user/login");
		web.ignoring().antMatchers("/api/v1/user/logout");
	}
}
