package com.wnet.desafio3.assembler;

import com.wnet.desafio3.dto.ClientDTO;
import com.wnet.desafio3.entity.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClientAssembler {
    private ModelMapper modelMapper;

    //Qdo precisar de uma EntityDTO
    public ClientDTO toModel(Client client){
        return modelMapper.map(client, ClientDTO.class);
    }


    //Qdo precisar de uma List<EntityDTO> normal...
    public List<ClientDTO> toCollectionModel(List<Client> clients){
        return clients.stream().map(this::toModel).collect(Collectors.toList());
    }

    //Qdo precisar de um Page<EntityDTO> paginada...
    public Page<ClientDTO> toColletionModelPage(Page<Client> clients) {
        return clients.map(this::toModel);
    }

    //de DTO para Entity
    public Client toEntity(ClientDTO dto) {
        return modelMapper.map(dto, Client.class);
    }
}
