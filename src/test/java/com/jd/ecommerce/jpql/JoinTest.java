package com.jd.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.Pedido;

public class JoinTest extends EntityManagerTest {
	
	@Test
	public void fazendoJoinPedidoPagamento() {
		String jpql = "select p from Pedido p join p.pagamento pag";
		
		TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> list = query.getResultList();

		Assert.assertTrue(list.size() == 1);
	}
}
