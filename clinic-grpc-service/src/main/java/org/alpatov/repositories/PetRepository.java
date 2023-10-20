package org.alpatov.repositories;

import org.alpatov.models.Pet;
import org.alpatov.models.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findAllByPetType(PetType petType);
    List<Pet> findAllByName(String name);
}
