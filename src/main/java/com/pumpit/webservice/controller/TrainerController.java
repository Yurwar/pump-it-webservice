package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.*;
import com.pumpit.webservice.controller.dto.ClientDto;
import com.pumpit.webservice.controller.dto.TrainerDto;
import com.pumpit.webservice.controller.dto.TrainerRegisterDto;
import com.pumpit.webservice.model.entity.Authority;
import com.pumpit.webservice.model.entity.Client;
import com.pumpit.webservice.model.entity.Trainer;
import com.pumpit.webservice.model.service.TrainerService;
import com.pumpit.webservice.util.exception.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/{id}")
    public TrainerDto getTrainerById(@PathVariable Long id) {
        Trainer trainer = trainerService.getTrainerById(id);

        return TrainerDto.builder()
                .username(trainer.getUsername())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .authorities(trainer.getAuthorities())
                .clientCount(trainer.getClients().size())
                .company(trainer.getCompany())
                .dateOfBirth(trainer.getDateOfBirth())
                .id(trainer.getId())
                .profilePicturePath(trainer.getProfilePicturePath())
                .sex(trainer.getSex())
                .build();
    }

    @GetMapping("/{id}/clients")
    public ClientResponsesDto getClientsForTrainer(@PathVariable Long id) {
        List<ClientDto> clientDtos= trainerService.getClientsForTrainerId(id).stream().map(client -> ClientDto.builder()
                .username(client.getUsername())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .authorities(client.getAuthorities())
                .dateOfBirth(client.getDateOfBirth())
                .id(client.getId())
                .profilePicturePath(client.getProfilePicturePath())
                .sex(client.getSex())
                .height(client.getHeight())
                .weight(client.getWeight())
                .trainerFirstName(Optional.ofNullable(client.getTrainer())
                        .map(Trainer::getFirstName).orElse(null))
                .trainerLastName(Optional.ofNullable(client.getTrainer())
                        .map(Trainer::getLastName).orElse(null))
                .build()).collect(Collectors.toList());
        return new ClientResponsesDto(clientDtos);
    }

    @PostMapping("/{id}/clients")
    public void addClientForTrainer(@PathVariable Long id,
                                    @RequestBody Client client) {
        trainerService.addClientForTrainerId(id, client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrainer(@PathVariable Long id, @RequestBody UpdateTrainerDto updateTrainerDto) {
        Trainer trainer = trainerService.getTrainerById(id);

        System.out.println("TRIGG");
        trainer.setFirstName(updateTrainerDto.getFirstName());
        trainer.setLastName(updateTrainerDto.getLastName());
        trainer.setSex(updateTrainerDto.getSex());
        trainer.setCompany(updateTrainerDto.getCompany());

        System.out.println(updateTrainerDto.getOldPassword());
        System.out.println(updateTrainerDto.getNewPassword());
        System.out.println(updateTrainerDto.getNewPasswordRepeat());

        if (Objects.nonNull(updateTrainerDto.getOldPassword()) &&
                Objects.nonNull(updateTrainerDto.getNewPassword()) &&
                Objects.nonNull(updateTrainerDto.getNewPasswordRepeat())) {
            System.out.println("CHECK PASS");
            if (!updateTrainerDto.getOldPassword().equals(trainer.getPassword())) {
                System.out.println("INC PASS");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!updateTrainerDto.getNewPassword().equals(updateTrainerDto.getNewPasswordRepeat())) {
                System.out.println("DO NOT MATCH");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                trainer.setPassword(updateTrainerDto.getNewPassword());
                trainerService.updateTrainer(trainer);
            }
        }
        System.out.println("SUCC");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewTrainer(@RequestBody TrainerRegisterDto trainerRegisterDto) {
        Trainer trainer = new Trainer();

        trainer.setUsername(trainerRegisterDto.getUsername());
        trainer.setCompany(trainerRegisterDto.getCompany());
        trainer.setFirstName(trainerRegisterDto.getFirstName());
        trainer.setLastName(trainerRegisterDto.getLastName());
        trainer.setPassword(trainerRegisterDto.getPassword());
        trainer.setDateOfBirth(trainerRegisterDto.getDateOfBirth());
        trainer.setSex(trainerRegisterDto.getSex());
        trainer.setEnabled(Boolean.TRUE);
        trainer.setAuthorities(Set.of(Authority.TRAINER));

        boolean success = true;
        try {
            trainerService.addNewTrainer(trainer);
        } catch (UserExistsException e) {
            success = false;
        }

        LoginResponseDto responseDto = new LoginResponseDto();

        if (success) {
            responseDto.setSuccessful(true);
            responseDto.setUser(buildUserDto(trainer));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    private UserDto buildUserDto(final Trainer trainer) {
        return UserDto.builder()
                .id(trainer.getId())
                .build();
    }
}
