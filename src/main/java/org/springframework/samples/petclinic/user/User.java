package org.springframework.samples.petclinic.user;

import java.time.LocalDateTime;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.persona.Persona;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)


public class User {
	@Id
	@Column(name = "username")
	@NotEmpty //Necesario pero no funciona hasta que no le das al enter
	protected String username;
	
	@Column(name = "password")
	@NotEmpty //Necesario pero no funciona hasta que no le das al enter
	protected String password;

	boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

	@CreatedBy
	@Column(name = "creator")
	private String creator;

	@CreatedDate
	@Column(name = "createdDate")
	private LocalDateTime createdDate;

	@LastModifiedBy
	@Column(name = "modifier")
	private String modifier;

	@LastModifiedDate
	@Column(name = "lastModifiedDate")
	private LocalDateTime lastModifiedDate;

}
