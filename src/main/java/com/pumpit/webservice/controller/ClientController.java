package com.pumpit.webservice.controller;

import com.pumpit.webservice.controller.dto.*;
import com.pumpit.webservice.model.entity.*;
import com.pumpit.webservice.model.service.ClientService;
import com.pumpit.webservice.util.exception.UserExistsException;
import org.dom4j.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);

        return ClientDto.builder()
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
                .build();
    }

    @GetMapping("/{id}/trainings")
    public List<Training> getTrainingsForClientId(@PathVariable Long id) {
        return clientService.getTrainingsForClientId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody UpdateClientDto updateClientDto) {
        Client client = clientService.getClientById(id);

        System.out.println("TRIGG");
        client.setFirstName(updateClientDto.getFirstName());
        client.setLastName(updateClientDto.getLastName());
        client.setSex(updateClientDto.getSex());
        client.setHeight(Integer.parseInt(updateClientDto.getHeight()));
        client.setWeight(Double.parseDouble(updateClientDto.getWeight()));

        System.out.println(updateClientDto.getOldPassword());
        System.out.println(updateClientDto.getNewPassword());
        System.out.println(updateClientDto.getNewPasswordRepeat());

        if (Objects.nonNull(updateClientDto.getOldPassword()) &&
                Objects.nonNull(updateClientDto.getNewPassword()) &&
                Objects.nonNull(updateClientDto.getNewPasswordRepeat())) {
            System.out.println("CHECK PASS");
            if (!updateClientDto.getOldPassword().equals(client.getPassword())) {
                System.out.println("INC PASS");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!updateClientDto.getNewPassword().equals(updateClientDto.getNewPasswordRepeat())) {
                System.out.println("DO NOT MATCH");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                client.setPassword(updateClientDto.getNewPassword());
            }
        }
        System.out.println("SUCC");
        clientService.updateClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registerNewClient(@RequestBody ClientRegisterDto clientRegisterDto) {
        Client client = new Client();

        client.setUsername(clientRegisterDto.getUsername());
        client.setFirstName(clientRegisterDto.getFirstName());
        client.setLastName(clientRegisterDto.getLastName());
        client.setPassword(clientRegisterDto.getPassword());
        client.setDateOfBirth(clientRegisterDto.getDateOfBirth());
        client.setSex(clientRegisterDto.getSex());
        client.setEnabled(Boolean.TRUE);
        client.setAuthorities(Set.of(Authority.CLIENT));

        boolean success = true;
        try {
            clientService.addNewClient(client);
        } catch (UserExistsException e) {
            success = false;
        }

        LoginResponseDto responseDto = new LoginResponseDto();

        if (success) {
            responseDto.setSuccessful(true);
            responseDto.setUser(buildUserDto(client));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    private UserDto buildUserDto(final Client client) {
        return UserDto.builder()
                .id(client.getId())
                .build();
    }
}
