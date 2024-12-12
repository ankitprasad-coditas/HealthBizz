package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.SurveyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyDataRepo extends JpaRepository<SurveyData, Long> {

    @Query("SELECT sd FROM SurveyData sd WHERE sd.dataCollector.id = :collectorId")
    List<SurveyData> findAllByDataCollectorId(@Param("collectorId") Long collectorId);
}
