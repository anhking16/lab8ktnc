package poly.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter{
@Autowired
BCryptPasswordEncoder pe;


@Bean
public BCryptPasswordEncoder getPasswordEncoder() {
return new BCryptPasswordEncoder();

}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication()
.withUser("user1").password(pe.encode("123")).roles("GUEST")
.and()
.withUser("user2").password(pe.encode("123")).roles("USER", "GUEST")
.and()
.withUser("user3").password(pe.encode("123")).roles("ADMIN", "USER", "GUEST");

}



@Override
protected void configure(HttpSecurity http) throws Exception {
//CSRF, CORS
http.csrf().disable().cors().disable();

//Phân quyền sử dụng
http.authorizeRequests()
.antMatchers("/home/index").permitAlI()
.anyRequest().authenticated();

//Giao diện đăng nhập
http.httpBasic();
}

}