package com.jd.ecommerce.relacionamento;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Categoria;

public class AutoRelacionamentoTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
	Categoria categoriaPai = new Categoria();
	categoriaPai.setNome("Eletrônicos");

	Categoria categoriaFilha = new Categoria();
	categoriaFilha.setNome("Celulares");
	categoriaFilha.setCategoriaPai(categoriaPai);

	entityManager.getTransaction().begin();
	entityManager.persist(categoriaPai);
	entityManager.persist(categoriaFilha);
	entityManager.getTransaction().commit();
	entityManager.clear();

	Categoria cFilha = entityManager.find(Categoria.class, categoriaFilha.getId());
	Categoria cPai = entityManager.find(Categoria.class, categoriaPai.getId());
	Assert.assertNotNull(cFilha.getCategoriaPai());
	Assert.assertFalse(cPai.getCategorias().isEmpty());
    }
}
