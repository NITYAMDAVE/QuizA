package org.nityam.quizruminate.Controllers;

import org.nityam.quizruminate.Models.User;
import org.nityam.quizruminate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){
        try{userService.login(user.getUsername(),user.getPassword());}
        catch(Exception e){
            return e.getMessage();
        }
        return "Login Successful";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
        try{
        userService.signup(user);
        }
        catch(Exception e){
            return e.getMessage();
        }
        return "Signup Successful";
    }
}
