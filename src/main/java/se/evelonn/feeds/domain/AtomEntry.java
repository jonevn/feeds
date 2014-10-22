package se.evelonn.feeds.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ATOM_ENTRY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@EqualsAndHashCode(callSuper = true)
public class AtomEntry extends AtomCommonAttributes {

	@Id
	@GeneratedValue
	@Column(name = "ENTRY_ID")
	private int id;

	@OneToMany
	@JoinTable(name = "ENTRY_AUTHOR", joinColumns = { @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ENTRY_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "PERSON_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomPerson> authors = new ArrayList<AtomPerson>();

	@OneToMany
	@JoinTable(name = "ENTRY_CATEGORY", joinColumns = { @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ENTRY_ID") }, inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomCategory> categories = new ArrayList<AtomCategory>();

	@OneToMany
	@JoinTable(name = "ENTRY_CONTRIBUTOR", joinColumns = { @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ENTRY_ID") }, inverseJoinColumns = { @JoinColumn(name = "CONTRIBUTOR_ID", referencedColumnName = "PERSON_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomPerson> contributors = new ArrayList<AtomPerson>();

	@OneToMany
	@JoinTable(name = "ENTRY_LINK", joinColumns = { @JoinColumn(name = "ENTRY_ID", referencedColumnName = "ENTRY_ID") }, inverseJoinColumns = { @JoinColumn(name = "LINK_ID", referencedColumnName = "LINK_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomLink> links = new ArrayList<AtomLink>();

	@Column(name = "PUBLISHED")
	private Timestamp published;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "UPDATED")
	private Timestamp updated;

	@Column(name = "RIGHTS")
	private String rights;

	@Column(name = "SUMMARY")
	private String summary;

	@OneToOne(mappedBy = "entry", cascade = CascadeType.ALL)
	private Event event;

	@ManyToOne
	@JoinColumn(name = "FEED_FK", referencedColumnName = "FEED_ID")
	private AtomFeed feed;

}
