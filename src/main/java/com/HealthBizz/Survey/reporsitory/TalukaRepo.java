package com.HealthBizz.Survey.reporsitory;

import com.HealthBizz.Survey.entity.Taluka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TalukaRepo extends JpaRepository<Taluka, Long> {
    Optional<Taluka> findByName(String name);

    @Query("SELECT t FROM Taluka t WHERE t.head = false")
    List<Taluka> findAllByNoHead();

}
