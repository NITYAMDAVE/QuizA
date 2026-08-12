package org.nityam.quizruminate.Services;

import org.nityam.quizruminate.Data_Structures.Pair;
import org.nityam.quizruminate.Models.Player;
import org.nityam.quizruminate.Models.Question;
import org.nityam.quizruminate.Models.Quiz;
import org.nityam.quizruminate.Models.QuizSession;
import org.nityam.quizruminate.Repository.QuestionRepo;
import org.nityam.quizruminate.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class QuizService {

    private final Map<String, QuizSession> activeSessions =
            new ConcurrentHashMap<>();

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;

    public void addQuestion(Question question) {
        Quiz quiz = quizRepo.findById(question.getQuiz().getId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        question.setQuiz(quiz);

        questionRepo.save(question);
    }

    public void createQuiz(Quiz quiz) {
        quizRepo.save(quiz);
    }

    public void removeQuiz(int quizid) {
        quizRepo.deleteById(quizid);
    }

    public void getQuestion(int questionid) {
        quizRepo.findById(questionid);
    }

    public void removeQuestion(int questionid) {
        quizRepo.deleteById(questionid);
    }

    public void getQuiz(int quizid) {
        quizRepo.findById(quizid);
    }

    public void updateQuestion(Question question) {
        questionRepo.save(question);
    }

    public void updateQuiz(Quiz quiz) {
        quizRepo.save(quiz);
    }

    public int getScore(List<Pair<String, Integer>> answers, String sessionid,Integer playerid) {
        int quizid= activeSessions.get(sessionid).getQuizId();
        int score = 0;
        for(Pair<String , Integer> pair : answers) {
            String answer = pair.getFirst();
            int questionid = pair.getSecond();
            String correct= questionRepo.findById(questionid).get().getAnswer();
            if(correct.equalsIgnoreCase(answer)) {score++;}
        }
        List<Player>players=activeSessions.get(sessionid).getPlayers();
        for(Player player : players) {
            if(player.getPlayerid().equals(playerid)) {player.setScore(score);}
        }
        return score;
    }

    public String checkAnswer(@RequestBody QuizSession session,@RequestParam int questionid,@RequestParam String answer) {
        int quizid= session.getQuizId();
        String correct= questionRepo.findById(questionid).get().getAnswer();
        if(correct.equals(answer.toLowerCase())) {return "Correct";}
        return  "Wrong";
    }

    public String addPlayer(Player player, String sessionid) {
        activeSessions.get(sessionid).getPlayers().add(player);
        return "Player added";
    }

    public String startQuizSession(Integer quizid) {

        QuizSession quizSession = new QuizSession();

        quizSession.setQuizId(quizid);
        quizSession.setSessionId(UUID.randomUUID().toString());
        quizSession.setStatus("Live");

        activeSessions.put(
                quizSession.getSessionId(),
                quizSession
        );

        return quizSession.getSessionId();
    }

    public List<String> endQuiz(String sessionId) {
        List<Player> players = activeSessions.get(sessionId).getPlayers();
        List<Pair<Integer, Player>> answers = new ArrayList<>();
        for(Player player : players) {
            answers.add(new Pair<>(player.getScore(), player));
        }
        answers.sort((a, b) -> Integer.compare(b.getFirst(), a.getFirst()));
        List<String> result = new ArrayList<>();
        for(Pair<Integer, Player> pair : answers) {result.add(pair.getSecond().getName());}
        activeSessions.remove(sessionId);
        return result;
    }

    public void beginQuizSession(String sessionId) {

    }
}
