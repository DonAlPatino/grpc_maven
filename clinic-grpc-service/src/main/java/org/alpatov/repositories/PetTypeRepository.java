package org.alpatov.repositories;

import org.alpatov.models.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Integer> {

    PetType findByName(String name);
}
