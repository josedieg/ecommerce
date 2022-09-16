package com.jd.ecommerce.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoId implements Serializable {

    private static final long serialVersionUID = 2885740052736369641L;

    @EqualsAndHashCode.Include
    Integer pedidoId;

    @EqualsAndHashCode.Include
    private Integer produtoId;

}
