package se.evelonn.feeds.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AtomSource extends AtomCommonAttributes {

	@OneToMany
	@JoinTable(name = "FEED_AUTHOR", joinColumns = { @JoinColumn(name = "FEED_ID", referencedColumnName = "FEED_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "PERSON_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomPerson> authors = new ArrayList<AtomPerson>();

	@OneToMany
	@JoinTable(name = "FEED_CATEGORY", joinColumns = { @JoinColumn(name = "FEED_ID", referencedColumnName = "FEED_ID") }, inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomCategory> categories = new ArrayList<AtomCategory>();

	@OneToMany
	@JoinTable(name = "FEED_CONTRIBUTOR", joinColumns = { @JoinColumn(name = "FEED_ID", referencedColumnName = "FEED_ID") }, inverseJoinColumns = { @JoinColumn(name = "CONTRIBUTOR_ID", referencedColumnName = "PERSON_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomPerson> contributors = new ArrayList<AtomPerson>();

	@OneToOne(mappedBy = "feed")
	private AtomGenerator generator;

	@Column(name = "TITLE")
	private String title;

	@OneToMany
	@JoinTable(name = "FEED_LINK", joinColumns = { @JoinColumn(name = "FEED_ID", referencedColumnName = "FEED_ID") }, inverseJoinColumns = { @JoinColumn(name = "LINK_ID", referencedColumnName = "LINK_ID") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AtomLink> links = new ArrayList<AtomLink>();

	@Column(name = "ICON")
	private String icon;

	@Column(name = "LOGO")
	private String logo;

	@Column(name = "RIGHTS")
	private String rights;

	@Column(name = "SUBTITLE")
	private String subtitle;

	@Column(name = "UPDATED")
	private Timestamp updated;
}
