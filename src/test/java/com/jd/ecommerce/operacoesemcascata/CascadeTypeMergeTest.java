package com.jd.ecommerce.operacoesemcascata;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.model.Cliente;
import com.jd.ecommerce.model.ItemPedido;
import com.jd.ecommerce.model.ItemPedidoId;
import com.jd.ecommerce.model.Pedido;
import com.jd.ecommerce.model.Produto;

public class CascadeTypeMergeTest extends EntityManagerTest {

    @Test
    public void atualizarPedidoComItens() {
	Cliente cliente = entityManager.find(Cliente.class, 1);
	Produto produto = entityManager.find(Produto.class, 1);

	Pedido pedido = new Pedido();
	pedido.setId(1);
	pedido.setCliente(cliente);
	pedido.setStatus(StatusPedido.AGUARDANDO);

	ItemPedido itemPedido = new ItemPedido();
	itemPedido.setId(new ItemPedidoId());
	itemPedido.getId().setPedidoId(pedido.getId());
	itemPedido.getId().setProdutoId(produto.getId());
	itemPedido.setPedido(pedido);
	itemPedido.setProduto(produto);
	itemPedido.setQuantidade(2);
	itemPedido.setPrecoProduto(produto.getPreco());

	/*
	 * CascadeType.PERSIST
	 */
	pedido.setItens(Arrays.asList(itemPedido));

	entityManager.getTransaction().begin();
	entityManager.merge(pedido);
	entityManager.getTransaction().commit();

	entityManager.clear();

	ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
	Assert.assertTrue(itemPedidoVerificacao.getQuantidade().equals(2));
    }

     @Test
    public void atualizarItemPedidoComPedido() {
	Cliente cliente = entityManager.find(Cliente.class, 1);
	Produto produto = entityManager.find(Produto.class, 1);

	Pedido pedido = new Pedido();
	pedido.setId(1);
	pedido.setCliente(cliente);
	pedido.setStatus(StatusPedido.PAGO);

	ItemPedido itemPedido = new ItemPedido();
	itemPedido.setId(new ItemPedidoId());
	itemPedido.getId().setPedidoId(pedido.getId());
	itemPedido.getId().setProdutoId(produto.getId());
	/*
	 * CascadeType.PERSIST
	 */
	itemPedido.setPedido(pedido); 
	itemPedido.setProduto(produto);
	itemPedido.setQuantidade(5);
	itemPedido.setPrecoProduto(produto.getPreco());

	pedido.setItens(Arrays.asList(itemPedido));

	entityManager.getTransaction().begin();
	entityManager.merge(itemPedido);
	entityManager.getTransaction().commit();

	entityManager.clear();

	ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
	Assert.assertTrue(StatusPedido.PAGO.equals(itemPedidoVerificacao.getPedido().getStatus()));
    }
}
