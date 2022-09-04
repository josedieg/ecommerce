package com.jd.ecommerce.relacionamento;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.StatusPagamento;
import com.jd.ecommerce.model.PagamentoCartao;
import com.jd.ecommerce.model.Pedido;

public class RelacionamentoOneToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {

	Pedido pedido = entityManager.find(Pedido.class, 1);

	PagamentoCartao pagamentoCartao = new PagamentoCartao();
	pagamentoCartao.setNumero("123");
	pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
	pagamentoCartao.setPedido(pedido);

	entityManager.getTransaction().begin();
	entityManager.persist(pagamentoCartao);
	entityManager.getTransaction().commit();
	entityManager.clear();

	Pedido p = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNotNull(p.getPagamento());

    }

}
