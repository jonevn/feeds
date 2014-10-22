package se.evelonn.feeds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ATOM_GENERATOR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@EqualsAndHashCode(callSuper = true)
public class AtomGenerator extends AtomCommonAttributes {

	@Id
	@GeneratedValue
	@Column(name = "GENERATOR_ID")
	private int id;

	@Column(name = "URI")
	private String uri;

	@Column(name = "VERSION")
	private String version;

	@Column(name = "TEXT")
	private String text;

	@OneToOne
	@JoinColumn(name = "FEED_ID")
	private AtomFeed feed;

}
