package org.nityam.quizruminate.Controllers;

import org.nityam.quizruminate.Models.Player;
import org.nityam.quizruminate.Services.PlayerService;
import org.nityam.quizruminate.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private PlayerService playerService;
//
//    @GetMapping("getCode")
//    public String getCode(@RequestParam int sessionid) {
//        return quizService.getCode(quizid);
//    }

    @PostMapping("addPlayer")
    public String addPlayer(@RequestBody Player player,@RequestParam String sessionid) {
        quizService.addPlayer(player,sessionid);
        return "Player added";
    }

//    @GetMapping("displayResult")
//    public String displayResult(@RequestParam int joincode,@RequestParam int playerid) {
//        playerService.displayResults(joincode,playerid);
//        return "Player Score"+;
//    }

}
