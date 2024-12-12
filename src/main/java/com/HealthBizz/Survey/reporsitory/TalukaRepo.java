package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.District;
import com.HealthBizz.Survey.entity.Taluka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TalukaRepo extends JpaRepository<Taluka, Long> {
    Optional<Taluka> findByName(String name);

    @Query("SELECT t FROM Taluka t WHERE t.head = false")
    List<Taluka> findAllByNoHead();

    @Query("SELECT t FROM Taluka t WHERE t.name = :talukaName AND t.district.id = :districtId")
    Optional<Taluka> findByTalukaNameAndDistrictId(@Param("talukaName") String stateName, @Param("districtId") Long districtId);

}
