package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {
}
