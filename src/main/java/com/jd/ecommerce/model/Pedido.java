package com.jd.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.listener.GerarNotaFiscalListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners({ GerarNotaFiscalListener.class })
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeInteger{

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<ItemPedido> itens;

    @Embedded
    private Endereco enderecoEntrega;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    private void calcularTotal() {
	if (this.itens != null) {
	    total = itens.stream().map(ItemPedido::getPrecoProduto)
		    .reduce(BigDecimal.ZERO, BigDecimal::add);
	}
    }

    @PrePersist
    public void aoPersistir() {
	calcularTotal();
	dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void aoAtualizar() {
	calcularTotal();
	dataUltimaAtualizacao = LocalDateTime.now();
    }
}
