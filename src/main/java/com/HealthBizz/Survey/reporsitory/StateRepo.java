package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.District;
import com.HealthBizz.Survey.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepo extends JpaRepository<State, Long> {

    @Query("SELECT d FROM State d WHERE d.name = :stateName AND d.country.name = :countryName")
    Optional<District> findByStateNameAndCountryName(@Param("stateName") String stateName, @Param("countryName") String countryNames);

    Optional<State> findByName(String name);

    @Query("SELECT s FROM State s WHERE s.head = false")
    List<State> findAllByNoHead();

}
