package com.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

/**
 * There will be the rating system.
 */
@Data
@Embeddable
public class Rating implements Serializable {

    @Basic
    @Column(nullable = false)
    private Double value;

    @Transient
    private Integer count;

    @Transient
    private Double sum;

    public Rating() {
        sum = 0d;
        count = 0;
    }

    public void upgrade(double value) {
        if (value > 10 || value < 0)
            throw new IllegalArgumentException("The rate cannot be more than 10 and less than 0");
        count++;
        sum += value;
        this.value = sum / count;
    }
}