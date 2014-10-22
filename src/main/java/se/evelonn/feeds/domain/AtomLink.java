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
@Table(name = "ATOM_LINK")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@EqualsAndHashCode(callSuper = true)
public class AtomLink extends AtomCommonAttributes {

	@Id
	@GeneratedValue
	@Column(name = "LINK_ID")
	private int id;

	@Column(name = "HREF")
	protected String href;

	@Column(name = "REL")
	protected String rel;

	@Column(name = "MEDIA_TYPE")
	protected String mediaType;

	@Column(name = "HREFLANG")
	protected String hreflang;

	@Column(name = "TITLE")
	protected String title;

	@Column(name = "LENGTH")
	protected String length;
}
