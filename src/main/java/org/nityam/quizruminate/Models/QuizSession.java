package org.nityam.quizruminate.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSession {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sessionId;
    private Integer quizId;
    private String status;
    private List<Player> players = new ArrayList<Player>();
}