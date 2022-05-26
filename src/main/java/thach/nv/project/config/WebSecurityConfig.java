package thach.nv.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Configuration
	@Order(1)
	public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/admin/**")
	        .authorizeRequests()
	    		.antMatchers("/admin/css/**","/admin/img/**", "/admin/js/**"
	    			,"/admin/scss/**", "/admin/vendor/**", "/admin/logout").permitAll()
	    		.antMatchers("/admin", "/admin/detail-*/**").hasAnyRole("ADMIN", "USER")
	    		.antMatchers("/admin/create-*", "/admin/save-*", 
	    				"/admin/update-*/**").hasAnyRole("ADMIN", "USER")
	            .antMatchers("/admin/delete-*/**", "/admin/nguoidung/**").hasRole("ADMIN")
	            .and()
	        .formLogin()
	            .loginPage("/admin/login").permitAll()
	            .usernameParameter("account")
	            .passwordParameter("password")
	            .defaultSuccessUrl("/admin", true)
	            .failureUrl("/admin/login?error")
	            .and()
	        .exceptionHandling()
	            .accessDeniedPage("/admin/error");
		}
	}
	
	@Configuration
	@Order(2)
	public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
	        .authorizeRequests()
	    		.anyRequest().permitAll()
	            .and()
	        .formLogin()
	            .loginPage("/login").permitAll()
	            .usernameParameter("account")
	            .passwordParameter("password")
	            .defaultSuccessUrl("/home", true)
	            .failureUrl("/login?error")
	            .and()	    
		    .sessionManagement()
		         .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		}
	}
}