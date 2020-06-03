package com.pumpit.webservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"clients", "trainings"})
@Entity
public class Trainer extends User {
    @Column(name = "company")
    private String company;

    @OneToMany(fetch = LAZY, mappedBy = "trainer")
    private Set<Client> clients;

    @OneToMany(fetch = LAZY, mappedBy = "trainer", cascade = ALL)
    private List<Training> trainings;
}
