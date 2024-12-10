package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepo extends JpaRepository<State, Long> {
    Optional<State> findByName(String name);
}
