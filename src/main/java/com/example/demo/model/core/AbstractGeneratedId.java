package com.example.demo.model.core;

import javax.persistence.*;

@MappedSuperclass
public class AbstractGeneratedId {
    @Id
    @GeneratedValue
    Long id;

    public Long getId() {
        return id;
    }
}
