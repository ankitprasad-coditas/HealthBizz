package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmailId(String emailId);
}
