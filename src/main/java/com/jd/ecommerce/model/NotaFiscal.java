package com.jd.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeInteger{

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name="fk_nota_fical_pedido"))
    private Pedido pedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_emissao")
    private Date dataEmissao;

    @Lob
    private byte[] xml;

}
