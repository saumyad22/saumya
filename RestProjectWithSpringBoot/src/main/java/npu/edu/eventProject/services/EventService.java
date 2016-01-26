package npu.edu.eventProject.services;

import java.util.List;

import npu.edu.eventProject.model.Event;



public interface EventService {
	
	Event save(Event event);
	List<Event> getList();
	Event getEvent(String eventId);

}
