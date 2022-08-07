package com.jd.ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.model.Cliente;
import com.jd.ecommerce.model.Pedido;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
	Cliente cliente = entityManager.find(Cliente.class, 1);

	Pedido pedido = new Pedido();
	pedido.setStatus(StatusPedido.AGUARDANDO);
	pedido.setDataConclusao(LocalDateTime.now());
	pedido.setClientePedido(cliente);
	pedido.setTotal(new BigDecimal(10));

	entityManager.getTransaction().begin();
	entityManager.persist(pedido);
	entityManager.getTransaction().commit();
	entityManager.clear();

	Pedido p = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNotNull(p.getClientePedido());
    }
}
