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
@Table(name = "ATOM_CATEGORY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@EqualsAndHashCode(callSuper = true)
public class AtomCategory extends AtomCommonAttributes {

	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private int id;

	@Column(name = "TERM")
	private String term;

	@Column(name = "SCHEME")
	private String scheme;

	@Column(name = "LABEL")
	private String label;
}
