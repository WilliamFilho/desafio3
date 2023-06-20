package com.wnet.desafio3.service;

import com.wnet.desafio3.assembler.ClientAssembler;
import com.wnet.desafio3.dto.ClientDTO;
import com.wnet.desafio3.repository.ClientRepository;
import com.wnet.desafio3.service.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClientService {

    //Usando Lombok para injetar as dependências através do construtor
    private ClientRepository repository;
    private ClientAssembler assembler;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return repository.findById(id).map(product -> assembler.toModel(product)).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado!"));
    }
}
