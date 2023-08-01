package mbuchatskyi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mbuchatskyi.model.User;
import mbuchatskyi.repository.UserRepository;
import mbuchatskyi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        
        throw new NullPointerException("User cannot be 'null'");
    }

    @Override
    public User readById(long id) {
			return userRepository.findById(id)
					.orElseThrow(
			        () -> new NullPointerException("User with id " + id + " not found"));
    }

    @Override
    public User update(User user){
        if (user != null) {
            readById(user.getId());
            return userRepository.save(user);
        }
        
        throw new NullPointerException("User cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}