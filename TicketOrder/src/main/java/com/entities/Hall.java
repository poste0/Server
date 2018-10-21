package com.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class Hall implements Serializable {

    @Id
    @Column(name = "hall_id", nullable = false)
    private UUID id;

    @Basic
    @Column(nullable = false)
    @Getter
    private Integer capacity;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "hall")
    private List<Session> sessions;

    public Hall(int capacity) {
        this.capacity = capacity;
        id = UUID.randomUUID();
    }
}