package se.evelonn.feeds.rest.representations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name = "Event", namespace = "http://feeds.evelonn.se/Event")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class EventRepresentation {

	@XmlElement
	private String personId;

	@XmlElement
	private String published;
}
