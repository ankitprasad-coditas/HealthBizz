package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.SurveyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyDataRepo extends JpaRepository<SurveyData,Long> {
}
