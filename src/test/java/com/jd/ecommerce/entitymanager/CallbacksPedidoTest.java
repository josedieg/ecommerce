package com.jd.ecommerce.entitymanager;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.model.Cliente;
import com.jd.ecommerce.model.Pedido;

public class CallbacksPedidoTest extends EntityManagerTest {
    
    @Test
    public void acionarCallbacksPedidoTest() {
	Pedido pedido = entityManager.find(Pedido.class, 1);
	pedido.setStatus(StatusPedido.CANCELADO);

	entityManager.getTransaction().begin();
	entityManager.merge(pedido);
	entityManager.getTransaction().commit();
	entityManager.clear();
	 
	Pedido p = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNotNull(p.getDataCriacao());
	Assert.assertNotNull(p.getDataUltimaAtualizacao());
    }
}
