package ctdDemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	 
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("pwd")).roles("USER");

    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http
	                //HTTP Basic authentication
	                .httpBasic()
	                .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/**").hasRole("USER")
	                .and()
	                .csrf().disable();
	    }


}
