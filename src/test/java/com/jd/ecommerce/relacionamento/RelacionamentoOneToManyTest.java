package com.jd.ecommerce.relacionamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.model.Cliente;
import com.jd.ecommerce.model.Pedido;

public class RelacionamentoOneToManyTest extends EntityManagerTest{
    @Test
    public void relacionamentoPedidoCliente() {
	Cliente cliente = entityManager.find(Cliente.class, 1);

	Pedido pedido = new Pedido();
	pedido.setStatus(StatusPedido.AGUARDANDO);
	pedido.setDataConclusao(LocalDateTime.now());
	pedido.setCliente(cliente);
	pedido.setTotal(new BigDecimal(10));

	entityManager.getTransaction().begin();
	entityManager.persist(pedido);
	entityManager.getTransaction().commit();
	entityManager.clear();

	Cliente c = entityManager.find(Cliente.class, cliente.getId());
	Assert.assertFalse(c.getPedidos().isEmpty());
    }
}
