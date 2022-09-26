package com.jd.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("PagamentoBoleto")
public class PagamentoBoleto extends Pagamento{

    @Column(name = "codigo_barras")
    private String codigoBarras;
}
