package com.HealthBizz.Survey.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emailId;

    private String password;

    private Long contactNumber;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "taluka_id")
    private Taluka taluka;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
