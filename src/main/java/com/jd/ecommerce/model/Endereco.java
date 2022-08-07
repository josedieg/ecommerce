package com.jd.ecommerce.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String cidade;

    private String estado;
}
