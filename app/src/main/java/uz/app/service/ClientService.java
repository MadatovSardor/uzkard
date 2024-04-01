package uz.app.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.app.dto.client.ClientBioDTO;
import uz.app.dto.client.ClientDTO;
import uz.app.dto.client.ClientPhoneDTO;
import uz.app.dto.client.ClientStatusDTO;
import uz.app.entity.ClientEntity;
import uz.app.enums.EntityStatus;
import uz.app.exception.ItemNotFoundException;
import uz.app.repository.ClientRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public ClientDTO create(ClientDTO dto, String profileName) {
        checkPhone(dto.getPhone());

        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setStatus(EntityStatus.ACTIVE);
        entity.setProfileName(profileName);

        clientRepository.save(entity);
        return toDTO(entity);
    }

    public ClientDTO updateBio(ClientBioDTO dto, String clientId) {
        ClientEntity entity = getById(clientId);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUpdatedDate(LocalDateTime.now());

        clientRepository.save(entity);
        return toDTO(entity);
    }

    public ClientDTO updatePhone(ClientPhoneDTO dto, String clientId) {
        ClientEntity entity = getById(clientId);
        entity.setPhone(dto.getPhone());
        entity.setUpdatedDate(LocalDateTime.now());

        clientRepository.save(entity);
        return toDTO(entity);
    }

    public ClientDTO updateStatus(ClientStatusDTO dto, String clientId) {
        ClientEntity entity = getById(clientId);

        if (dto.getStatus().equals(entity.getStatus())) {
            return toDTO(entity);
        }

        entity.setStatus(dto.getStatus());

        clientRepository.save(entity);
        return toDTO(entity);
    }

    public PageImpl<ClientDTO> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ClientEntity> entityPage = clientRepository.findAll(pageable);

        List<ClientDTO> dtoList = new ArrayList<>();

        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public ClientDTO get(String clientId, String profileName) {
        ClientEntity entity;
        if (profileName.equals("profile")) {
            entity = clientRepository
                    .findByIdAndProfileName(clientId, profileName)
                    .orElseThrow(() -> {
                        log.warn("Not found");
                        return new ItemNotFoundException("Not found!");
                    });

            return toDTO(entity);
        }
        entity = getById(clientId);

        return toDTO(entity);
    }

    public PageImpl<ClientDTO> paginationListByProfileName(int page, int size, String profileName) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ClientEntity> entityPage = clientRepository.findAllByProfileName(pageable, profileName);

        List<ClientDTO> dtoList = new ArrayList<>();

        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public ClientEntity getById(String clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(() -> {
                    log.warn("Not found {}", clientId);
                    return new ItemNotFoundException("Not found!");
                });
    }

    public void checkPhone(String phone) {
        if (clientRepository.findByPhone(phone).isPresent()) {
            log.warn("Phone Number Unique {}", phone);
            throw new ItemNotFoundException("Phone Number Unique!");
        }
    }

    public ClientDTO toDTO(ClientEntity entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setProfileName(entity.getProfileName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }
}
