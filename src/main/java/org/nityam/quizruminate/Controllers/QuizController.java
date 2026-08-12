package org.nityam.quizruminate.Controllers;

import org.hibernate.Session;
import org.nityam.quizruminate.Data_Structures.Pair;
import org.nityam.quizruminate.Models.Question;
import org.nityam.quizruminate.Models.Quiz;
import org.nityam.quizruminate.Models.QuizSession;
import org.nityam.quizruminate.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/Quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody Question question) {
        try{quizService.addQuestion(question);}
        catch(Exception e){
            return "Error while adding question"+e.getMessage();
        }
        return "Question added successfully";
    }

    @PostMapping("/createQuiz")
    public String createQuiz(@RequestBody Quiz quiz) {
        try{quizService.createQuiz(quiz);}
        catch (Exception e) {
            return "Error while creating quiz "+e.getMessage();
        }
        return "Quiz created successfully";
    }
//
    @PostMapping("/removeQuiz")
    public String removeQuiz(@RequestParam int quizid) {
        try{
            quizService.removeQuiz(quizid);
        }
        catch(Exception e) {
            return "Error while removing quiz "+e.getMessage();
        }
        return "Quiz removed successfully";
    }

    @PostMapping("/removeQuestion")
    public String removeQuestion(@RequestParam int quizid) {
        try {
            quizService.removeQuestion(quizid);
        } catch (Exception e) {
            return "Error while removing question "+e.getMessage();
        }
        return "Question removed successfully";
    }

    @GetMapping("/getQuestion")
    public String getQuestion(@RequestParam int questionid) {
        try{
        quizService.getQuestion(questionid);
        }catch(Exception e) {
            return "Error while getting question "+e.getMessage();
        }
        return "Question retrieved successfully";
    }

    @GetMapping("getQuiz")
    public String getQuiz(@RequestParam int quizid) {
        try {
            quizService.getQuiz(quizid);
        } catch (Exception e) {
            return "Error while getting quiz "+e.getMessage();
        }
        return "Quiz fetched successfully";
    }

    @PutMapping("updateQuestion")
    public String updateQuestion(@RequestBody Question question) {
        quizService.updateQuestion(question);
        return "Question updated successfully";
    }

    @PutMapping("updateQuiz")
    public String updateQuiz(@RequestBody Quiz quiz) {
        quizService.updateQuiz(quiz);
        return "Quiz updated successfully";
    }

    @PostMapping("getScore")
    public int getScore(@RequestBody List<Pair<String,Integer>> answers, @RequestParam String sessionid,@RequestParam Integer playerid) {
        return quizService.getScore(answers,sessionid,playerid);

    }

    @PostMapping("checkAnswer")
    public String checkAnswer(@RequestBody QuizSession session,@RequestParam int questionid,@RequestParam String answer) {
        return quizService.checkAnswer(session,questionid,answer);
    }

    @GetMapping("startQuiz")
    public String startQuizSession(@RequestParam Integer quizid) {
        System.out.println(quizid);
        return quizService.startQuizSession(quizid);
    }

    @GetMapping("endQuiz")
    public List<String> endQuiz(@RequestParam String  sessionId) {
        return quizService.endQuiz(sessionId);
    }

    @GetMapping("beginQuiz")
    public String beginQuizSession(@RequestParam String  sessionId) {
        quizService.beginQuizSession(sessionId);
        return "Quiz started successfully";
    }
}
