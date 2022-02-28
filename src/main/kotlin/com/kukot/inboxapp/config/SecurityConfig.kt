

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.http.HttpStatus
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {
    
    override fun configure(http: HttpSecurity) {
        // @formatter: off
        http
            .authorizeRequests({ req -> req
                    .antMatchers("", "/error").permitAll()
                    .anyRequest().authenticated()
            })
            .exceptionHandling({
                e -> e.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            })
            .csrf({
                c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            })
            .logout({
                l -> l.logoutSuccessUrl("/").permitAll()
            })
            .oauth2Login()

        // @formatter: on
    }
}