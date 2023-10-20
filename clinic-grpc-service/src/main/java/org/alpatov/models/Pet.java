package org.alpatov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Builder
    public Pet(int id, String name, PetType petType, LocalDate birthDate) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.birthDate = birthDate;
    }

    @Column(name = "name")
    private String name;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private PetType petType;
    @Column(name = "birth_date")
    private LocalDate birthDate;

}
