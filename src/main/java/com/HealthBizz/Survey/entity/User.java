package com.HealthBizz.Survey.entity;

import com.HealthBizz.Survey.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emailId;

    private String password;

    private Long contactNumber;
    
    @Enumerated(EnumType.STRING)
    private Roles role;

    private String location;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "taluka_id")
    private Taluka taluka;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
