package com.jd.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Pedido;

public class JoinTest extends EntityManagerTest {

	@Test
	public void fazendoJoinFethPedido() {
		String jpql = "select p from Pedido p join fetch p.itens where p.id = 1";

		TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
		Pedido pedido = query.getSingleResult();
		Assert.assertFalse(pedido.getItens().isEmpty());
	}

	@Test
	public void fazendoLeftJoinPedidoPagamento() {
		String jpql = "select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'";

		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> list = query.getResultList();
		Assert.assertFalse(list.isEmpty());
	}

	@Test
	public void fazendoJoinPedidoPagamento() {
		String jpql = "select p from Pedido p join p.pagamento pag";

		TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> list = query.getResultList();

		Assert.assertTrue(list.size() == 1);
	}
}
