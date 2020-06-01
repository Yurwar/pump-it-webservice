package com.pumpit.webservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "exerciseSetRepeat")
@Entity
@Table(name = "trainings")
public class Training {
    @Id
    @SequenceGenerator(name = "trainingsIdSeq", sequenceName = "trainings_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainingsIdSeq")
    @Column(name = "id")
    private long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ElementCollection
    @CollectionTable(name = "exercise_sets_repeat",
            joinColumns = @JoinColumn(name = "training_id"))
    @Column(name = "exercise_set_repeat_count")
    @MapKeyJoinColumn(name = "exercise_set_id")
    private Map<ExerciseSet, Integer> exerciseSetRepeat;
}
