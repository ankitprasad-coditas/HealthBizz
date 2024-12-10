package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {
    Optional<District> findByName(String name);
}
