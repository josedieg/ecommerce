package com.jd.ecommerce;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.model.Categoria;

public class EstrategiaChavePrimaraTest extends EntityManagerTest {

    
    @Test
    public void testarEstrategiaChave() {
	Categoria categoria = new Categoria();
	categoria.setNome("Eletronicos");

	entityManager.getTransaction().begin();
	entityManager.persist(categoria);
	entityManager.getTransaction().commit();
	entityManager.clear();

	entityManager.find(Categoria.class, categoria.getId());
	
	Assert.assertNotNull(categoria);
    }

}
