package com.pumpit.webservice.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Client extends User {
    @Column
    private double weight;
    @Column
    private int height;
    @ManyToOne(fetch = FetchType.EAGER)
    private Trainer trainer;
}
