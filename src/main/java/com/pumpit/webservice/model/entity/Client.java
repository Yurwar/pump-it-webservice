package com.pumpit.webservice.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"trainer"})
@Entity
public class Client extends User {
    @Column
    private double weight;
    @Column
    private int height;
    @ManyToOne(fetch = FetchType.EAGER)
    private Trainer trainer;
}
