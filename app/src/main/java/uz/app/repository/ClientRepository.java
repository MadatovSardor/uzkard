package uz.app.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.entity.ClientEntity;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    Page<ClientEntity> findAllByProfileName(Pageable pageable, String profileName);

    Optional<ClientEntity> findByIdAndProfileName(String id, String profileName);

    Optional<ClientEntity> findByPhone(String phone);

}