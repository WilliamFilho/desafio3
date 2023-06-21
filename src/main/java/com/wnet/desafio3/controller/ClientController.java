package com.wnet.desafio3.controller;

import com.wnet.desafio3.assembler.ClientAssembler;
import com.wnet.desafio3.dto.ClientDTO;
import com.wnet.desafio3.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    //Usando Lombok para injetar as dependências através do construtor
    private ClientService service;
    private ClientAssembler assembler;
    @GetMapping("/desafio")
    public String desafio(){
        return "Desafio 3 - Prof: Nélio Alves - Aluno: William Filho";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    //Paginação /clients?page=0&size=6&sort=name
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

}
