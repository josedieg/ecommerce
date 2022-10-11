package com.jd.ecommerce.operacoesemcascata;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Pedido;

public class CascadeTypeRemoveTest extends EntityManagerTest {

    @Test
    public void removerPedidoEItens() {
	Pedido pedido = entityManager.find(Pedido.class, 2);

	entityManager.getTransaction().begin();
	entityManager.remove(pedido);
	entityManager.getTransaction().commit();

	entityManager.clear();

	Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNull(pedidoVerificacao);
    }

}
