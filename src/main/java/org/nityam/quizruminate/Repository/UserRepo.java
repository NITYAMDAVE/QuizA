package org.nityam.quizruminate.Repository;

import jakarta.persistence.Id;
import org.nityam.quizruminate.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByusername(String username);
}
