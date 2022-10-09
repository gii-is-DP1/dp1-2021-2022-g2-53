package org.springframework.samples.petris.persona;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petris.auditoria.Auditoria;
import org.springframework.samples.petris.jugador.Jugador;
import org.springframework.samples.petris.model.BaseEntity;
import org.springframework.samples.petris.model.Person;
import org.springframework.samples.petris.user.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personas")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Persona  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	public boolean isNew() {
		return this.id == null;
	}

	@Column(name = "first_name")
	@NotEmpty
	protected String firstName;

	@Column(name = "last_name")
	@NotEmpty
	protected String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user", referencedColumnName = "username")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	private List<Jugador> jugadores;

	@CreatedBy
	@Column(name = "creator")
	private String creator;

	@CreatedDate
	@Column(name = "created_Date")
	private LocalDateTime createdDate;

	@LastModifiedBy
	@Column(name = "modified_by")
	private String modifier;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private LocalDateTime lastModifiedDate;

	
	

	

}
