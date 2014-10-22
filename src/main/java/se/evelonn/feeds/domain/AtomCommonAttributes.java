package se.evelonn.feeds.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AtomCommonAttributes {

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "BASE")
	private String base;
}
