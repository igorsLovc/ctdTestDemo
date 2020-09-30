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
		
        auth.inMemoryAuthentication()
                .withUser("user").password("{bcrypt}$2a$10$s73K7/k9vxo5Wce5bLpxnegpgu2Sl3RmUArrU1N5KjxhlmruL4ylC").roles("USER");

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
