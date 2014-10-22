package se.evelonn.feeds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ATOM_PERSON")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@EqualsAndHashCode(callSuper = true)
public class AtomPerson extends AtomCommonAttributes {

	@Id
	@GeneratedValue
	@Column(name = "PERSON_ID")
	private int id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "URI")
	private String uri;

	@Column(name = "EMAIL")
	private String email;
}
