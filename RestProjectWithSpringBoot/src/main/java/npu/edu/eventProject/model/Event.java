package npu.edu.eventProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@NotNull
	@Size(max = 64)
	@Column(name = "eventId", nullable = false, updatable = false)
	private String eventId;

	@NotNull
	@Size(max = 64)
	@Column(name = "eventName", nullable = false)
	private String eventName;
	
	
	@NotNull
	@Column(name="eventDate",nullable = false)
	private String eventDate;
	
	public String getEventId() {
		return eventId;
	}

	@NotNull
	@Column(name="eventLocation",nullable = false)
	private String eventLocation;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

}
