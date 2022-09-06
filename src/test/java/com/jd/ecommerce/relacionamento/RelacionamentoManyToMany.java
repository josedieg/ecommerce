package com.jd.ecommerce.relacionamento;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Categoria;
import com.jd.ecommerce.model.Produto;

public class RelacionamentoManyToMany extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Categoria categoria = entityManager.find(Categoria.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setCategorias(Arrays.asList(categoria));
        entityManager.getTransaction().commit();
        entityManager.clear();
        
        Categoria c = entityManager.find(Categoria.class, 1);
        Assert.assertFalse(c.getProdutos().isEmpty());
    }
}
