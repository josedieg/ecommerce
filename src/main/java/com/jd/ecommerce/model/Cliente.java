package com.jd.ecommerce.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jd.ecommerce.enuns.SexoCliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SecondaryTable(name = "cliente_detalhe")
@Entity
@Table(name = "cliente")
public class Cliente extends EntidadeInteger{

    private String nome;

    private LocalDate dataNascimento;

    @Transient
    private String primeiroNome;

    @Enumerated(EnumType.STRING)
    @Column(table = "cliente_detalhe")
    private SexoCliente sexo;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @ElementCollection
    @CollectionTable(name = "cliente_contato", 
    	joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;

    @PostLoad
    public void configPrimeiroNome() {
	if (nome != null && !nome.isBlank()) {
	    int index = nome.indexOf(" ");
	    if (index > -1) {
		primeiroNome = nome.substring(0, index);
	    }
	}
    }

}
