package com.example.demo.model.core;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@MappedSuperclass
public class AbstractGeneratedIdAuditableTime extends AbstractGeneratedId {

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Instant lastModifiedDate;

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    @PrePersist
    protected void prePersist() {
        this.createdDate = Instant.now();
        this.lastModifiedDate = Instant.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.lastModifiedDate =  Instant.now();
    }
}
