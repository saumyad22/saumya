package npu.edu.eventProject.services;


import java.util.List;

import npu.edu.eventProject.model.User;

public interface UserService {

    User save(User user);

    List<User> getList();
    
    User checkUser(User user);

}
