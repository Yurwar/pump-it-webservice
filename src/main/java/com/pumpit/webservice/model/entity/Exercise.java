package com.pumpit.webservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @SequenceGenerator(name = "exercisesIdSeq", sequenceName = "exercises_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercisesIdSeq")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
}
