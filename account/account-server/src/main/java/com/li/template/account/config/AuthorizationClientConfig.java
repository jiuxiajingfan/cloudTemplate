package com.li.template.account.config;

import com.li.template.config.WebMvcAuthorizationManager;
import com.li.template.exception.MyAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class AuthorizationClientConfig {

	@Bean
	WebMvcAuthorizationManager webMvcAuthorizationManager(){
		return new WebMvcAuthorizationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		//uri放行
		String[] ignoreUrls = new String[]{"/*.html","/favicon.ico","/webjars/**","/*/v3/api-docs**","/v3/api-docs/**"};
		http.authorizeHttpRequests(authorize ->
						authorize.requestMatchers(ignoreUrls).permitAll()
								// 鉴权管理器配置
                				.anyRequest().access(webMvcAuthorizationManager())
				)
				.exceptionHandling((exceptions) -> exceptions
						.authenticationEntryPoint(new MyAuthenticationEntryPoint())
				);
		return http.build();
	}
}