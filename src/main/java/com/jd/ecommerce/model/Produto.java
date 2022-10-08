package com.jd.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produto extends EntidadeInteger {

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(length = 255, nullable = false)
    private String nome;

    @Column(columnDefinition = "varchar(275) not null default 'descricao'")
    private String descricao;

    @Column(precision = 19, scale = 2)
    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria", 
    	foreignKey = @ForeignKey(name = "fk_produto_categoria"), 
    	inverseForeignKey = @ForeignKey(name = "fk_categoria_produto"), 
    	joinColumns = @JoinColumn(columnDefinition = "produto_id"),
    	inverseJoinColumns = @JoinColumn(columnDefinition = "id")
    )
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(columnDefinition = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(columnDefinition = "produto_id"))
    private List<Atributo> atributos;
}
