package com.jd.ecommerce.mapavancado;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Cliente;

public class PropriedadeTransientTest extends EntityManagerTest {

    @Test
    public void validarPrimeiroNome() {
	Cliente cliente = entityManager.find(Cliente.class, 1);
	Assert.assertEquals("Jose", cliente.getPrimeiroNome());
    }
}
