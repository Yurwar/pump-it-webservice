package com.pumpit.webservice.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercise_sets")
public class ExerciseSet {
    @Id
    @SequenceGenerator(name = "exerciseSetIdSeq", sequenceName = "exercise_set_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exerciseSetIdSeq")
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(name = "weight")
    private int weight;

    @Column(name = "repeat_count")
    private int repeatCount;
}
