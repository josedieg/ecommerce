package com.jd.ecommerce;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.model.Produto;

public class ConsultandoRegistrosTest extends EntityManagerTest{
    
    @Test
    public void busarPorIdentificador() {
	Produto produto = entityManager.find(Produto.class, 1);

	Assert.assertNotNull(produto);
    }
}
