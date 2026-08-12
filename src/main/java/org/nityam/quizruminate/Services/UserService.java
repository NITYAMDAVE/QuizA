package org.nityam.quizruminate.Services;

import org.nityam.quizruminate.Models.User;
import org.nityam.quizruminate.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public String login(String username, String password) {
        User user = userRepo.findByusername(username);
        if(user == null){
            return "Username is invalid";
        }
        String repoPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(password);
        if (repoPassword.equals(password)) {
            return "Hello Welcome Back";
        }
        return "Password is invalid";
    }

    public String signup(User user) {
        String username = user.getUsername();
        User u= userRepo.findByusername(username);
        if(u!=null){
            return "Try Another Username";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "Register Successfully <br> Your  Username: " + user.getUsername()+"<br>Your Password: " + user.getPassword();
    }
}