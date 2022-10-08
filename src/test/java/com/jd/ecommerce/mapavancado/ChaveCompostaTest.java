package com.jd.ecommerce.mapavancado;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.ItemPedido;
import com.jd.ecommerce.model.ItemPedidoId;
import com.jd.ecommerce.model.Pedido;
import com.jd.ecommerce.model.Produto;

public class ChaveCompostaTest extends EntityManagerTest {
    @Test
    public void salvarItem() {

	Produto produto = entityManager.find(Produto.class, 1);
	Pedido pedido = entityManager.find(Pedido.class, 1);

	entityManager.getTransaction().begin();
	
	ItemPedido itemPedido = new ItemPedido();
	itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
	itemPedido.setPedido(pedido);
	itemPedido.setProduto(produto);
	itemPedido.setPrecoProduto(produto.getPreco());
	itemPedido.setQuantidade(1);

	entityManager.persist(itemPedido);

	entityManager.getTransaction().commit();

	entityManager.clear();

	Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNotNull(pedidoVerificacao);
	Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void bucarItem() {
	Produto produto = entityManager.find(Produto.class, 1);
	Pedido pedido = entityManager.find(Pedido.class, 1);
	ItemPedido itemPedido = entityManager.find(
		ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));

	Assert.assertNotNull(itemPedido);
    }
}
