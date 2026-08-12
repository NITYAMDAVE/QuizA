package org.nityam.quizruminate.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer playerid;
    private String name;
    private Integer score=0;
    private Integer correctAnswers=0;
    private Integer totalAnswers=0;
}