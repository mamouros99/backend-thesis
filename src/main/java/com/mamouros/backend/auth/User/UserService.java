package com.mamouros.backend.auth.User;

import com.mamouros.backend.auth.FenixEdu;
import com.mamouros.backend.exceptions.UserNotFoundException;
import com.mamouros.backend.helpers.GlobalHelper;
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

    public User findMyUser(){

        User requester = GlobalHelper.getUserFromSecurityContext();

        return findByUsername(requester.getUsername());

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

    public void updateUser(String username, Role role) {

        User user = findByUsername(username);
        user.setRole(role);
        usersRepository.save(user);
    }

    public Iterable<User> findAllUsers(){
        return usersRepository.findAll();
    }

    public void toggleReceiveQuestionById(String username) {

        //check if admin or same username
        User requester = GlobalHelper.getUserFromSecurityContext();

        if(!requester.getRole().equals(Role.ADMIN) && !requester.getUsername().equals(username))
            throw new RuntimeException("You don't have permission");


        User user = findByUsername(username);
        user.setReceiveQuestions(!user.getReceiveQuestions());
        usersRepository.save(user);

    }
}
