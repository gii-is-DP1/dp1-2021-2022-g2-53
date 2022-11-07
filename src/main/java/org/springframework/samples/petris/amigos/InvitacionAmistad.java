package org.springframework.samples.petris.amigos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.samples.petris.model.BaseEntity;
import org.springframework.samples.petris.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "invitacion_amigo")
public class InvitacionAmistad extends BaseEntity {

	@OneToOne()
	@JoinColumn(name = "username1")
	private User user1;
	@OneToOne()
	@JoinColumn(name = "username2")
	private User user2;

}
