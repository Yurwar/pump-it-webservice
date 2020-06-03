package com.pumpit.webservice.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@ToString(exclude = {"trainer"})
@EqualsAndHashCode(callSuper = true, exclude = {"trainer"})
@Entity
public class Client extends User {
    @Column
    private double weight;
    @Column
    private int height;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL, mappedBy = "client")
    private List<Training> trainings;
}
