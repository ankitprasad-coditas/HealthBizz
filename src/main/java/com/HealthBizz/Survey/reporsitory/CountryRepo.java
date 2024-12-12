package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);
}
