package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {

    @Query("SELECT d FROM District d WHERE d.name = :districtName AND d.state.id = :stateId")
    Optional<District> findByDistrictNameAndStateId(@Param("districtName") String districtName, @Param("stateId") Long stateId);

    Optional<District> findByName(String name);

    @Query("SELECT d FROM District d WHERE d.head = false")
    List<District> findAllByNoHead();
}
