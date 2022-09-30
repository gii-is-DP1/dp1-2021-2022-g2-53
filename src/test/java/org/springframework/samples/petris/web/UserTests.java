package org.springframework.samples.petris.web;



import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petris.configuration.SecurityConfiguration;
import org.springframework.samples.petris.persona.PersonaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;




@WebMvcTest(value = PersonaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserTests {




}
