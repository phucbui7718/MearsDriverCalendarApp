package services;

/**
 * Created by Phuc on 3/1/2016.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        auth.inMemoryAuthentication().withUser("123").password("123456").roles("USER");
    }

  @Override
    protected void configure(HttpSecurity http) throws Exception{

       http.authorizeRequests().antMatchers("/suclogin/**").hasRole("USER")
               .and().formLogin().loginPage("/login.html")
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
  }

}
