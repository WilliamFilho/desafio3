package com.wnet.desafio3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//Não expõe o modelo, filtra campos necessários criando uma View...
public class ClientDTO {
    private Long id;

    @NotEmpty(message = "Campo não pode ser vazio!")
    @NotBlank(message = "Entre com um nome válido!")
    private String name;

    //@CPF(message = "Entre com CPF válido!")
    private String cpf;

    @Positive(message = "Somente número positivo!")
    private Double income;

    @PastOrPresent(message = "Entre com uma data válida!")
    private LocalDate birthDate;

    private Integer children;
}
