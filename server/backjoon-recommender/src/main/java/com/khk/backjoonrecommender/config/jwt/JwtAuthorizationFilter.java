package com.khk.backjoonrecommender.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.khk.backjoonrecommender.config.auth.PrincipalDetails;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private final JwtProperties jwtProperties;
	private final UserRepository userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, JwtProperties jwtProperties) {
		super(authenticationManager);
		this.userRepository = userRepository;
		this.jwtProperties = jwtProperties;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String jwtHeader = request.getHeader("Authorization");

		if (jwtHeader == null || !jwtHeader.startsWith(jwtProperties.getTOKEN_PREFIX())) {
			chain.doFilter(request, response);
			return;
		}

		String jwtToken = request.getHeader("Authorization")
				.replace(jwtProperties.getTOKEN_PREFIX(), "");

		String account = JWT.require(Algorithm.HMAC512(jwtProperties.getHashKey())).build()
				.verify(jwtToken)
				.getClaim("account")
				.asString();

		if (account != null) {
			User userEntity = userRepository.findByUsername(account);

			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
}
