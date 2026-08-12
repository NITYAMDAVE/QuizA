package org.nityam.quizruminate.Repository;

import org.nityam.quizruminate.Models.Question;
import org.nityam.quizruminate.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {

}
