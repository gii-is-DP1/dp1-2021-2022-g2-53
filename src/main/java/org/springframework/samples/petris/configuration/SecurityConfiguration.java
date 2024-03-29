package org.springframework.samples.petris.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.samples.petris.auditoria.AuditoriaImpl;
import org.springframework.samples.petris.user.User;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
@SpringBootApplication
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**", "/webjars/**", "/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/", "/oups").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/personas/auditoria").hasAuthority("admin")
				.antMatchers("/games/herramientasAdmin").hasAnyAuthority("admin")
				.antMatchers("/games/listGames").hasAuthority("admin")
				.antMatchers("/games/mostrarpartidas").hasAuthority("admin")
				.antMatchers("/games/mostrarpartidasencurso").hasAnyAuthority("admin")
				.antMatchers("/games/**").hasAuthority("persona")
				.antMatchers("/jugadores/**").hasAuthority("admin")
				.antMatchers("/personas/edit/**").hasAuthority("admin")
				.antMatchers("/personas/delete/**").hasAuthority("admin")
				.antMatchers("/users/amigos").permitAll()
				.antMatchers("/users/**").permitAll()
				.antMatchers("/users/find?username=**").permitAll()
				.antMatchers("/users/encontrados").permitAll()
				.antMatchers("/users/buscar/user").permitAll()
				.antMatchers("/users/encontrado").permitAll()
				.antMatchers("/users/encontrados").permitAll()
				.antMatchers("/users/delete/**").permitAll()
				.antMatchers("/personas").hasAuthority("persona")
				.antMatchers("/people").hasAuthority("persona")
				.antMatchers("/estadisticas").hasAuthority("persona")
				.antMatchers("/personas/estadisticas").hasAuthority("persona")
				.antMatchers("/chats/chat").hasAuthority("persona")
				.antMatchers("/chats/**").hasAuthority("persona")
				.antMatchers("/personas/seguroview").hasAuthority("persona")
				.antMatchers("/personas/editperfil/**").hasAuthority("persona")
				.antMatchers("/personas/partidaspersona").hasAuthority("persona")
				.antMatchers("/personas/partidaspeople").hasAuthority("persona")
				.antMatchers("/personas/registro/**").hasAuthority("admin")
				.antMatchers("/personas/listPersonas").hasAuthority("admin")
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/owners/**").hasAnyAuthority("owner", "admin")
				.antMatchers("/register").permitAll()
				.antMatchers("/welcome").permitAll()
				.antMatchers("/invitacionAmigo").permitAll()
				.antMatchers("/invitacionAmigo/**").permitAll()
				.antMatchers("/error").permitAll()
				.antMatchers("/errorIntentoBorrado").permitAll()
				.antMatchers("/errorMismoUsuarioEnPartida").hasAuthority("persona")
				.antMatchers("/errorSuplantacion").hasAuthority("persona").anyRequest().denyAll().and().formLogin()
				
				
				/* .loginPage("/login") */
				.failureUrl("/login-error").and().logout().logoutSuccessUrl("/");
		// Configuración para que funcione la consola de administración
		// de la BD H2 (deshabilitar las cabeceras de protección contra
		// ataques de tipo csrf y habilitar los framesets si su contenido
		// se sirve desde esta misma página.
		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password,enabled " + "from users " + "where username = ?")
				.authoritiesByUsernameQuery("select username, authority " + "from authorities " + "where username = ?")
				.passwordEncoder(passwordEncoder());
	}


	
	@Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditoriaImpl();
	}

}
