package org.alpatov.service;

import io.grpc.Metadata;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import org.alpatov.service.PetService;
import org.alpatov.dto.PetRequestDTO;
import org.alpatov.dto.PetResponseDTO;
import org.alpatov.grpc.server.grpc_server.pet.PetOuterClass;
import org.alpatov.grpc.server.grpc_server.pet.PetServiceGrpc;

import static io.grpc.Status.NOT_FOUND;

/**
 * @author Viacheslav Alpatov
 */
@GrpcService
@RequiredArgsConstructor
public class GrpcPetServiceImpl extends PetServiceGrpc.PetServiceImplBase {
    private final PetService petService;

    @Override
    public void createPet(PetOuterClass.CreatePetRequest request,
                          StreamObserver<PetOuterClass.CreatePetResponse> responseObserver) {

        PetRequestDTO petRequestDTO = PetRequestDTO.builder()
                .petName(request.getPet().getPetName())
                .petType(request.getPet().getPetType())
                .petBirthDate(request.getPet().getPetBirthDate())
                .build();

        PetOuterClass.CreatePetResponse response = PetOuterClass.CreatePetResponse
                .newBuilder()
                .setPetId(petService.createPet(petRequestDTO))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findByIDPet(PetOuterClass.FindByIdPetRequest request,
                            StreamObserver<PetOuterClass.FindByIdPetResponse> responseObserver) {

        PetResponseDTO pet = petService.findByIDPet(request.getPetId());


        // Обработка ошибок
        if (pet == null) {
            Metadata.Key<PetOuterClass.ErrorResponse> errorResponseKey =
                    ProtoUtils.keyForProto(PetOuterClass.ErrorResponse.getDefaultInstance());
            PetOuterClass.ErrorResponse errorResponse = PetOuterClass.ErrorResponse.newBuilder()
                    .setErrorName("This pet with id = " + request.getPetId() + " is not in the database")
                    .build();
            Metadata metadata = new Metadata();
            metadata.put(errorResponseKey, errorResponse);
            responseObserver.onError(
                    NOT_FOUND.withDescription("This pet with id = " + request.getPetId() + " is not found")
                            .asRuntimeException(metadata)
            );
            return;
        }
        PetOuterClass.FindByIdPetResponse response = PetOuterClass.FindByIdPetResponse
                .newBuilder()
                .setPet(PetOuterClass.FindByIdPetResponse.Pet
                        .newBuilder()
                        .setPetName(pet.petName())
                        .setPetType(pet.petType())
                        .build())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
