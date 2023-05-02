package com.mamouros.backend.auth.User;

import com.mamouros.backend.auth.FenixEdu;
import com.mamouros.backend.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    public User findByUsername(String username){
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public User saveUserFromFenix(FenixEdu fenix){
        Role role = fenix.getUsername().equals("ist189548") ? Role.ADMIN : Role.VIEWER;
        User user = new User(fenix.getName(), fenix.getUsername(), fenix.getEmail(), role);
        usersRepository.save(user);
        return user;
    }

    public void deleteById(String username){
        usersRepository.deleteById(username);
    }

    public void updateUser(User user) {

        findByUsername(user.getUsername());
        usersRepository.save(user);
    }

    public Iterable<User> findAllUsers(){
        return usersRepository.findAll();
    }
}
