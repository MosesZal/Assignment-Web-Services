package moses.zal.Individual.Assignment;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IndividualAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndividualAssignmentApplication.class, args);
	}

	/*This bean provides the default configurations related to Keycloak. The SecurityConfig class is used to customize specific
	configurations.*/
	@Bean
	public KeycloakSpringBootConfigResolver keycloakConfigResolver () {
		return new KeycloakSpringBootConfigResolver();
	}

}
