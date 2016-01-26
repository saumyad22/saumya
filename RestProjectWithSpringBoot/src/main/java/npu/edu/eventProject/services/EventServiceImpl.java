package npu.edu.eventProject.services;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import npu.edu.eventProject.model.Event;
import npu.edu.eventProject.model.EventRepository;
import npu.edu.eventProject.model.UserAlreadyExistsException;



@Service
@Validated
public class EventServiceImpl implements EventService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);
	private final EventRepository eventRepository;

	@Inject
	public EventServiceImpl(final EventRepository repository) {
		this.eventRepository = repository;
	}

	@Override
	@Transactional
	public Event save(Event event) {

		LOGGER.debug("Creating {}", event);
		Event existing = eventRepository.findOne(event.getEventId());
		if (existing != null) {
			throw new UserAlreadyExistsException(
					String.format("There already exists a event with id=%s", event.getEventId()));
		}
		return eventRepository.save(event);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Event> getList() {
		LOGGER.debug("Retrieving the list of all users");
		return eventRepository.findAll();
	}

	@Override
	public Event getEvent(String eventId) {
		return eventRepository.findOne(eventId);
	}

}
