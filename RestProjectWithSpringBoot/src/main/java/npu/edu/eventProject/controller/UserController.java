package npu.edu.eventProject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import npu.edu.eventProject.model.User;
import npu.edu.eventProject.model.UserAlreadyExistsException;
import npu.edu.eventProject.services.UserService;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;

	@Inject
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public User createUser(@RequestBody @Valid final User user) {
		LOGGER.debug("Received request to create the {}", user);
		return userService.save(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public User checkUser(@RequestBody @Valid final User user) {
		LOGGER.debug("Received request to create the {}", user);
		return userService.checkUser(user);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, consumes = { "application/json" }, produces = {
			"application/json" })
	public List<User> listUsers() {
		LOGGER.debug("Received request to list all users");
		return userService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
		return e.getMessage();
	}

}
