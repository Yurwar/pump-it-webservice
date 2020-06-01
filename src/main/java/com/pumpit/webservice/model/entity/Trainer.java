package com.pumpit.webservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"clients"})
@Entity
public class Trainer extends User {
    @Column(name = "company")
    private String company;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainer", cascade = CascadeType.PERSIST)
    private Set<Client> clients;
}
