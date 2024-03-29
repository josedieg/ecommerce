package com.jd.ecommerce.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jd.ecommerce.enuns.StatusPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Table(name = "pagamento")
public abstract class Pagamento extends EntidadeInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name="fk_pagamento_pedido"))
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
}
