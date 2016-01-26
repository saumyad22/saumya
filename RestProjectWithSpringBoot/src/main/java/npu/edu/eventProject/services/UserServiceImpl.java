package npu.edu.eventProject.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import npu.edu.eventProject.model.User;
import npu.edu.eventProject.model.UserAlreadyExistsException;
import npu.edu.eventProject.model.UserRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;

    @Inject
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {
        LOGGER.debug("Creating {}", user);
        User existing = repository.findOne(user.getUsername());
        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getUsername()));
        }
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }

	@Override
	@Transactional(readOnly = true)
	public User checkUser(User user) {
		try{
			
		User findOne = repository.findOne(user.getUsername());
		if(!findOne.getPassword().equals(user.getPassword())){
			User fakeUser = new User(user.getUsername(), "User Not Found");
			return fakeUser;
		}
		}catch(Exception ex){
			User fakeUser = new User(user.getUsername(), "User Not Found");
			return fakeUser;
		}
		return null;
	}

}
