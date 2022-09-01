package com.jd.ecommerce.relacionamento;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Pedido;

public class RemoverEntidadeRelacionadaTest extends EntityManagerTest {

    @Test
    public void removerEntidadeRelacionada() {
	Pedido pedido = entityManager.find(Pedido.class, 1);

	Assert.assertFalse(pedido.getItens().isEmpty());

	entityManager.getTransaction().begin();
	pedido.getItens().forEach(item -> {
	    entityManager.remove(item);
	});
	entityManager.remove(pedido);
	entityManager.getTransaction().commit();

	entityManager.clear();

	Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
	Assert.assertNull(pedidoVerificacao);
    }
}
