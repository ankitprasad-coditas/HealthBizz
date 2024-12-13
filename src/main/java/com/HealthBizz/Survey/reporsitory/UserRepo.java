package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmailId(String emailId);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.emailId = :emailId AND (u.country IS NOT NULL OR u.state IS NOT NULL OR u.district IS NOT NULL OR u.taluka IS NOT NULL)")
    boolean existsByEmailIdAndIsHeadOfAnyRegion(@Param("emailId") String emailId);

    List<User> findAll();
}
