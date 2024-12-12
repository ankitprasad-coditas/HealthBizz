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
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean dataCollector = false;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "taluka_id", nullable = false)
    private Taluka taluka;

    @JsonBackReference
    @OneToMany(mappedBy = "city")
    private Set<User> users;

}
