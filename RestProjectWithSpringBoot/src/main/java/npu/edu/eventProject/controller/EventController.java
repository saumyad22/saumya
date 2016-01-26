package npu.edu.eventProject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import npu.edu.eventProject.model.Event;
import npu.edu.eventProject.model.UserAlreadyExistsException;
import npu.edu.eventProject.services.EventService;


@RestController
public class EventController {


	
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
	    private final EventService eventService;
	    
	    @Inject
	    public EventController(final EventService eventService) {
	        this.eventService = eventService;
	    }

	    @RequestMapping(value = "/event", method = RequestMethod.POST,consumes = {"application/json"},
	            produces = {"application/json"})
	    public Event createUser(@RequestBody @Valid final Event event) {
	        LOGGER.debug("Received request to create the {}", event);
	        return eventService.save(event);
	    }

	    @RequestMapping(value = "/events", method = RequestMethod.GET, consumes = {"application/json"},
	            produces = {"application/json"})
	    public List<Event> listUsers() {
	        LOGGER.debug("Received request to list all events");
	        return eventService.getList();
	    }
	    
	    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET, consumes = {"application/json"},
	            produces = {"application/json"})
	    
	    @ResponseStatus(HttpStatus.OK)
	    public
	    @ResponseBody
	    Event geEvent(@PathVariable("id") String id,
	                             HttpServletRequest request, HttpServletResponse response) throws Exception {
	       System.out.println(id);
	    	Event event = eventService.getEvent(id);
	      
	        return event;
	    }
	    
	    
	    
	    
	    
	   

	    @ExceptionHandler
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
	        return e.getMessage();
	    }


}
