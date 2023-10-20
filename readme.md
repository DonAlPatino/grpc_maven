Создание и тестирование gRPC сервиса (Spring Boot приложение)
https://habr.com/ru/articles/767130/

Work repository

1. Build for get all neededgenerated class
mvn clean package
2. For Idea mark as generated source root in interface-grps target 
3. grpcurl for windows need \
grpcurl -plaintext -d "{\"pet\":{\"pet_name\":\"Joo\",\"pet_type\":\"dog\",\"pet_birth_date\":\"2023-09-01\"}}" localhost:9090 org.alpatov.grpc.server.grpc_server.pet.PetService/CreatePet

grpcurl --v -plaintext -d "{\"pet_id\":1}" localhost:9090 org.alpatov.grpc.server.grpc_server.pet.PetService/FindByIDPet

Dump db in container
pg_dump -U postgres -W pet_clinic > /tmp/pet_clinic.dump