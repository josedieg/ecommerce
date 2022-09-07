package com.jd.ecommerce.entitymanager;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Categoria;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void verificarEstadoTransient() {
	Categoria categoriaTransient = new Categoria();

	Assert.assertNull(entityManagerFactory.getPersistenceUnitUtil()
		.getIdentifier(categoriaTransient));
    }

    @Test
    public void verificarEstadoManaged() {
	Categoria categoria = entityManager.find(Categoria.class, 1);

	Assert.assertTrue(entityManager.contains(categoria));
    }

    @Test
    public void verificarEstadoDetach() {
	Categoria categoria = entityManager.find(Categoria.class, 1);
	entityManager.detach(categoria);

	Assert.assertNotNull(entityManagerFactory.getPersistenceUnitUtil()
		.getIdentifier(categoria));
    }
    
    @Test
    public void verificarEstadoRemoved() {
	Categoria categoria = entityManager.find(Categoria.class, 2);
	Assert.assertNotNull(entityManagerFactory.getPersistenceUnitUtil()
		.getIdentifier(categoria));
	Assert.assertTrue(entityManager.contains(categoria));
	entityManager.remove(categoria);
	Assert.assertFalse(entityManager.contains(categoria));
    }
}
