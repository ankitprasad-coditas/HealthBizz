package com.HealthBizz.Survey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean head=false;

    @JsonBackReference
    @OneToMany(mappedBy = "country")
    Set<State> states;

    @JsonBackReference
    @OneToMany(mappedBy = "country")
    private Set<User> users;

}
