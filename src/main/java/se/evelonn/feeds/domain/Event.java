package se.evelonn.feeds.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EVENT")
@Data
public class Event {

	@Id
	@GeneratedValue
	@Column(name = "EVENT_ID")
	private int id;

	@Column(name = "PERSON_ID")
	private String personId;

	@OneToOne
	@JoinColumn(name = "ENTRY_ID", nullable = true)
	private AtomEntry entry;

	@Column(name = "PUBLISHED")
	private Timestamp published;
}
