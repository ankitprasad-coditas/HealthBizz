package com.HealthBizz.Survey.reporsitory;


import com.HealthBizz.Survey.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    /**
     * @param: name -> Role name
     * @return: Returns the role if found or return null
     */
    Optional<Role> findByName(String name);
}
