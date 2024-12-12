package com.HealthBizz.Survey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Taluka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean head = false;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @JsonBackReference
    @OneToMany(mappedBy = "taluka")
    private Set<City> cities;

    @JsonBackReference
    @OneToMany(mappedBy = "taluka")
    private Set<User> users;

}
