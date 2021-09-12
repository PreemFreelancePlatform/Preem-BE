
package com.bzwilson.bflp.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * Once the client has gained authorization, users need to gain authentication. This class is response for handling that.
 * It also configures which roles have access to which endpoints. So controls the users' access!
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig
        extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }

    /**
     * This method configures which roles can access which endpoints
     *
     * @param http Our HttpSecurity object that is maintains by Spring
     * @throws Exception in case the configurations fails
     */
    @Override
    public void configure(HttpSecurity http)
            throws
            Exception {
        // our antMatchers control which roles of users have access to which endpoints
        // we must order our antmatchers from most restrictive to least restrictive.
        // So restrict at method level before restricting at endpoint level.
        // permitAll = everyone and their brother
        // authenticated = any authenticated, signed in, user
        // hasAnyRole = must be authenticated and be assigned this role!
        http.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/createnewfreelancer",
                        "/createnewcustomer")
                .permitAll()
                .antMatchers(HttpMethod.PUT)
                .hasAnyRole("ADMIN")
                .antMatchers("/customer/**", "/freelancer/**",
                        "/oauth/revoke-token",
                        "/logout")
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        // http.requiresChannel().anyRequest().requiresSecure(); required for https

        // disable the creation and use of Cross Site Request Forgery Tokens.
        // These tokens require coordination with the front end client that is beyond the scope of this class.
        // See https://www.yawintutor.com/how-to-enable-and-disable-csrf/ for more information
        http.csrf().disable();


        // this disables all of the security response headers. This is necessary for access to the H2 Console.
        // Normally, Spring Security would include headers such as
        //     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
        //     Pragma: no-cache
        //     Expires: 0
        //     X-Content-Type-Options: nosniff
        //     X-Frame-Options: DENY
        //     X-XSS-Protection: 1; mode=block
        http.headers()
                .frameOptions()
                .disable();

        // This application implements its own logout procedure so disable the one built into Spring Security
        http.logout()
                .disable();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}