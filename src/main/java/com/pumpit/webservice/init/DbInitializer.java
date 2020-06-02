package com.pumpit.webservice.init;

import com.pumpit.webservice.model.entity.*;
import com.pumpit.webservice.model.repository.ExerciseRepository;
import com.pumpit.webservice.model.repository.ExerciseSetRepository;
import com.pumpit.webservice.model.repository.TrainingRepository;
import com.pumpit.webservice.model.service.ClientService;
import com.pumpit.webservice.model.service.TrainerService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DbInitializer {
    private ClientService clientService;
    private TrainerService trainerService;
    private TrainingRepository trainingRepository;
    private ExerciseRepository exerciseRepository;
    private ExerciseSetRepository exerciseSetRepository;

    public DbInitializer(ClientService clientService,
                         TrainerService trainerService,
                         TrainingRepository trainingRepository,
                         ExerciseRepository exerciseRepository,
                         ExerciseSetRepository exerciseSetRepository) {
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseSetRepository = exerciseSetRepository;
    }

    @PostConstruct
    public void initDb() {
        Client client1 = new Client();
        client1.setFirstName("Yurii");
        client1.setLastName("Matora");
        client1.setPassword("123456");
        client1.setUsername("Yurwar");
        client1.setSex(Sex.MALE);
        client1.setAuthorities(Collections.singleton(Authority.CLIENT));
        client1.setProfilePicturePath("/images/client_1.png");
        client1.setWeight(80);
        client1.setHeight(192);
        client1.setDateOfBirth(LocalDate.of(2000, 6, 5));
        client1.setEnabled(true);

        Client client2 = new Client();
        client2.setFirstName("Roman");
        client2.setLastName("Dovhopoliuk");
        client2.setPassword("123456");
        client2.setUsername("CodeGangsta44");
        client2.setSex(Sex.MALE);
        client2.setAuthorities(Collections.singleton(Authority.CLIENT));
        client2.setProfilePicturePath("/images/client_2.png");
        client2.setWeight(85);
        client2.setHeight(185);
        client2.setDateOfBirth(LocalDate.of(2000, 2, 27));
        client2.setEnabled(true);

        clientService.addNewClient(client1);
        clientService.addNewClient(client2);

        Trainer trainer1 = new Trainer();
        trainer1.setFirstName("Mark");
        trainer1.setLastName("Zubach");
        trainer1.setPassword("123456789");
        trainer1.setUsername("Helvetica");
        trainer1.setSex(Sex.MALE);
        trainer1.setAuthorities(Collections.singleton(Authority.TRAINER));
        trainer1.setProfilePicturePath("/images/trainer_1.png");
        trainer1.setDateOfBirth(LocalDate.of(2000, 5, 31));
        trainer1.setEnabled(true);
        trainer1.setClients(Set.of(client1, client2));
        trainer1.setCompany("SportGallera");

        trainerService.addNewTrainer(trainer1);

        Exercise exercise1 = new Exercise();
        exercise1.setName("Bench press");
        Exercise exercise2 = new Exercise();
        exercise2.setName("Lifting up");
        Exercise exercise3 = new Exercise();
        exercise3.setName("Press exercise");
        Exercise exercise4 = new Exercise();
        exercise4.setName("Hyperextension");

        exerciseRepository.saveAll(List.of(exercise1, exercise2, exercise3, exercise4));

        ExerciseSet exerciseSet1 = new ExerciseSet();
        exerciseSet1.setExercise(exercise1);
        exerciseSet1.setRepeatCount(10);
        exerciseSet1.setWeight(15);

        ExerciseSet exerciseSet2 = new ExerciseSet();
        exerciseSet2.setExercise(exercise2);
        exerciseSet2.setRepeatCount(20);
        exerciseSet2.setWeight(20);

        ExerciseSet exerciseSet3 = new ExerciseSet();
        exerciseSet3.setExercise(exercise3);
        exerciseSet3.setRepeatCount(15);
        exerciseSet3.setWeight(80);

        ExerciseSet exerciseSet4 = new ExerciseSet();
        exerciseSet4.setExercise(exercise4);
        exerciseSet4.setRepeatCount(35);
        exerciseSet4.setWeight(40);

        exerciseSetRepository.saveAll(List.of(exerciseSet1, exerciseSet2, exerciseSet3, exerciseSet4));

        Map<ExerciseSet, Integer> exerciseSetRepeat1 = new HashMap<>();
        exerciseSetRepeat1.put(exerciseSet1, 5);
        exerciseSetRepeat1.put(exerciseSet2, 15);
        exerciseSetRepeat1.put(exerciseSet4, 3);

        Map<ExerciseSet, Integer> exerciseSetRepeat2 = new HashMap<>();
        exerciseSetRepeat2.put(exerciseSet1, 6);
        exerciseSetRepeat2.put(exerciseSet2, 8);
        exerciseSetRepeat2.put(exerciseSet3, 10);

        Training training1 = new Training();
        training1.setClient(client1);
        training1.setTrainer(trainer1);
        training1.setStartTime(LocalDateTime.of(2020, 6, 2, 11, 0));
        training1.setEndTime(LocalDateTime.of(2020, 6, 2, 12, 0));
        training1.setExerciseSetRepeat(exerciseSetRepeat1);

        Training training2 = new Training();
        training2.setClient(client2);
        training2.setTrainer(trainer1);
        training2.setStartTime(LocalDateTime.of(2020, 6, 1, 15, 0));
        training2.setEndTime(LocalDateTime.of(2020, 6, 2, 16, 30));
        training2.setExerciseSetRepeat(exerciseSetRepeat2);

        Training training3 = new Training();
        training3.setClient(client1);
        training3.setTrainer(trainer1);
        training3.setStartTime(LocalDateTime.of(2020, 6, 10, 17, 20));
        training3.setStartTime(LocalDateTime.of(2020, 6, 10, 18, 20));

        trainingRepository.saveAll(List.of(training1, training2, training3));
    }
}
