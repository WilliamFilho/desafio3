package com.wnet.desafio3.service;

import com.wnet.desafio3.assembler.ClientAssembler;
import com.wnet.desafio3.dto.ClientDTO;
import com.wnet.desafio3.entity.Client;
import com.wnet.desafio3.repository.ClientRepository;
import com.wnet.desafio3.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    //Usando Lombok para injetar as dependências através do construtor
    private ClientRepository repository;
    private ClientAssembler assembler;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return repository.findById(id).map(client -> assembler.toModel(client)).orElseThrow(()-> new ResourceNotFoundException("Cliente Inexistente!"));
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        return assembler.toColletionModelPage(repository.findAll(pageable));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) { //já envio convertido (DTO)
        Client newClient = assembler.toEntity(dto);
        repository.save(newClient);
        return assembler.toModel(newClient);
    }

    @Transactional
    public ClientDTO uptade(Long id, ClientDTO dto) { //já envio convertido (DTO) /Refatorado...
        return repository.findById(id).map(client -> {
            client = assembler.toEntity(dto);
            client.setId(id);
            repository.save(client);
            return assembler.toModel(client);
        }).orElseThrow(()-> new ResourceNotFoundException("Cliente Inexistente!"));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
            if(!repository.existsById(id)){
                throw new EntityNotFoundException("Cliente Inexistente!");
            }
            repository.deleteById(id);
    }

}
