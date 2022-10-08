package com.jd.ecommerce.operacoesemcascata;

import java.time.LocalDateTime;
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

public class CascadeTypePersistTest extends EntityManagerTest {

    @Test
    public void persistirPedidoComItens() {
	Cliente cliente = entityManager.find(Cliente.class, 1);
	Produto produto = entityManager.find(Produto.class, 1);

	Pedido pedido = new Pedido();
	pedido.setDataCriacao(LocalDateTime.now());
	pedido.setCliente(cliente);
	pedido.setTotal(produto.getPreco());
	pedido.setStatus(StatusPedido.AGUARDANDO);

	ItemPedido itemPedido = new ItemPedido();
	itemPedido.setId(new ItemPedidoId());
	itemPedido.setPedido(pedido);
	itemPedido.setProduto(produto);
	itemPedido.setQuantidade(1);
	itemPedido.setPrecoProduto(produto.getPreco());
	/*
	 * CascadeType.PERSIST
	 */
	pedido.setItens(Arrays.asList(itemPedido));

	entityManager.getTransaction().begin();
	entityManager.persist(pedido);
	entityManager.getTransaction().commit();

	entityManager.clear();

	Pedido p = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNotNull(p);
	Assert.assertFalse(p.getItens().isEmpty());

    }

    @Test
    public void persistirItemPedidoComPedido() {
	Cliente cliente = entityManager.find(Cliente.class, 2);
	Produto produto = entityManager.find(Produto.class, 1);

	Pedido pedido = new Pedido(); 
	pedido.setDataCriacao(LocalDateTime.now());
	pedido.setCliente(cliente);
	pedido.setTotal(produto.getPreco());
	pedido.setStatus(StatusPedido.AGUARDANDO);

	ItemPedido itemPedido = new ItemPedido();
	itemPedido.setId(new ItemPedidoId());
	itemPedido.setPedido(pedido);
	itemPedido.setProduto(produto);
	itemPedido.setQuantidade(1);
	itemPedido.setPrecoProduto(produto.getPreco());

	entityManager.getTransaction().begin();
	entityManager.persist(itemPedido);
	entityManager.getTransaction().commit();

	entityManager.clear();

	Pedido p = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNotNull(p);
    }

}
