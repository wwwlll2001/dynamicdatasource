package demo.dynamicdatasource.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void modifyUser(String newUsername) {
        userRepository.findAll().forEach(user -> user.setName(newUsername));
    }
}
