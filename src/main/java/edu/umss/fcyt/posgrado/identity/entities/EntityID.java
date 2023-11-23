package edu.umss.fcyt.posgrado.identity.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class EntityID<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

}
