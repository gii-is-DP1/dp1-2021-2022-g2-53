package org.springframework.samples.petris.user;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)

public class User {
	@Id
	String username;

	String password;

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
