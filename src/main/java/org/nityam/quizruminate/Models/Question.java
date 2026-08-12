package org.nityam.quizruminate.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    private String type;
    @Getter
    @Setter
    private String answer;
    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

}
