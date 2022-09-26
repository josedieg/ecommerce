package com.jd.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("PagamentoCartao")
public class PagamentoCartao extends Pagamento {

    @Column(name = "numero")
    private String numero;
}
