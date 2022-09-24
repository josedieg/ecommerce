package com.jd.ecommerce.mapavancado;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Atributo;
import com.jd.ecommerce.model.Produto;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarAtributos() {
	entityManager.getTransaction().begin();

	Produto produto = entityManager.find(Produto.class, 1);
	produto.setAtributos(Arrays.asList(new Atributo("capa", "rigida")));

	entityManager.getTransaction().commit();

	entityManager.clear();

	Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
	Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
    }
    
    @Test
    public void aplicarTags() {
	entityManager.getTransaction().begin();

	Produto produto = entityManager.find(Produto.class, 1);
	produto.setTags(Arrays.asList("livro-fisico", "livro-digital"));

	entityManager.getTransaction().commit();

	entityManager.clear();

	Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
	Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
    }
}
