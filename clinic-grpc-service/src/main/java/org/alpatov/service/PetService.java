package org.alpatov.service;

import org.alpatov.dto.PetRequestDTO;
import org.alpatov.dto.PetResponseDTO;

import java.util.List;

/**
 * @author Viacheslav Alpatov
 */
public interface PetService {
    int createPet(PetRequestDTO pet);

    PetResponseDTO findByIDPet(int id);

    List<PetResponseDTO> findAllPet();

    List<PetResponseDTO> findAllByNamePet(String name);

    void deletePet(int petId);

    PetResponseDTO updatePet(PetRequestDTO pet);
}
