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
    public Set<ClientDto> getClientsForTrainer(@PathVariable Long id) {
        return trainerService.getClientsForTrainerId(id).stream().map(client -> ClientDto.builder()
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
                .build()).collect(Collectors.toSet());
    }

    @PostMapping("/{id}/clients")
    public void addClientForTrainer(@PathVariable Long id,
                                    @RequestBody Client client) {
        trainerService.addClientForTrainerId(id, client);
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
