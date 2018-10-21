package com.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
public class Film implements Serializable {

    @Id
    @Column(name = "film_id", nullable = false)
    private UUID id;

    @Basic
    @Column(nullable = false)
    private String title;

    @Basic
    @Column(nullable = false)
    private String description;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "film")
    private List<Session> sessions;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "rating"))
    })
    private Rating rating;

    @Column
    private Date startDate;

    public Film(String title, String description, Rating rating , Date startDate) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.startDate = startDate;
        id = UUID.randomUUID();
    }
}