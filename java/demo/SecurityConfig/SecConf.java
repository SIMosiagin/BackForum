package demo.SecurityConfig;

import demo.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public SecConf(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/sign_up").permitAll()
                .antMatchers("/*/floor1/**").hasRole("USER")
                .antMatchers("/*/floor2/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthFilter(authenticationManager()))
                .addFilter(new JWTAuthzFilter(authenticationManager(),customUserDetailService));
    }



}
