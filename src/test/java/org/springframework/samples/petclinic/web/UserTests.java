package org.springframework.samples.petclinic.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.persona.PersonaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;


@WebMvcTest(value = PersonaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UserTests {

    @Autowired
    private PersonaController personaController;
    
    @Autowired
	private MockMvc mockMvc;

    //@Test
    //public void testGetView() throws Exception{
    //    mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("personas/register"));
    //}
}
