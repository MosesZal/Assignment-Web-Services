package moses.zal.Individual.Assignment.Security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    /*A class provided by Spring Security. Its purpose is to map authorities or roles from an external identity provider
    (IdP) to Spring Security's representation of authorities.*/
    SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
    // A class provided by Keycloak for handling the authentication process.
    KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
    // Here, in short, we are simply specifying Keycloak as the IdP mentioned above.
    keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
    /*The AuthenticationManagerBuilder is a class provided by Spring Security that allows you to configure how your
    application performs authentication which is via keycloakAuthenticationProvider in that case */
    authenticationManagerBuilder.authenticationProvider(keycloakAuthenticationProvider);
  }

  // SessionAuthenticationStrategy defines the strategy for handling authenticated user sessions.
  // RegisterSessionAuthenticationStrategy is responsible for registering the authenticated session in the session registry.
  // SessionRegistryImpl is responsible for maintaining a registry of active user sessions.
  // This ensures that the user won't need to authenticate again with every subsequent request.
  @Bean
  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  }

  // HttpSessionManager provides functionality and abstractions related to session management in a web application context.
  @Bean
  @Override
  @ConditionalOnMissingBean(HttpSessionManager.class)
  protected HttpSessionManager httpSessionManager() {
    return new HttpSessionManager();
  }

  /*Since admins are also users, and since we specified that any request must be authenticated, the ant matchers related
  to the user role aren't mandatory. They're just included for good practice. Removing "anyRequest().authenticated()"
  and the URL for a certain endpoint, from antMatchers, will make requests related to that endpoint available without the
  need for authentication.*/
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    super.configure(httpSecurity);
    httpSecurity
            .authorizeRequests()
            .antMatchers("/api/users", "/api/newuser", "/api/users/{ID}").hasRole("admin")
            .antMatchers("/api/posts", "/api/newpost", "/api/updatepost/{ID}").hasRole("user")
            .antMatchers( "/api/deletepost/{ID}", "/api/posts/{ID}").hasRole("user")
            .anyRequest().authenticated();
    httpSecurity.csrf().disable();
  }
}
