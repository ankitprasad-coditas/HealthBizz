package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);

    @Query("SELECT c FROM City c WHERE c.dataCollector = false")
    List<City> findAllByNoDataCollector();

    @Query("SELECT c FROM City c WHERE c.name = :cityName AND c.taluka.id = :talukaId")
    Optional<City> findByCityNameAndTalukaId(@Param("cityName") String cityName, @Param("talukaId") Long talukaId);
}
