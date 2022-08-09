package com.jd.ecommerce.relacionamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.model.Cliente;
import com.jd.ecommerce.model.ItemPedido;
import com.jd.ecommerce.model.Pedido;
import com.jd.ecommerce.model.Produto;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

    @Test
    public void relacionamentoPedidoCliente() {
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
    
    @Test
    public void verificarRelacionamentoItemPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(new BigDecimal(111));
        pedido.setClientePedido(cliente);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertNotNull(itemPedidoVerificacao.getPedido());
        Assert.assertNotNull(itemPedidoVerificacao.getProduto());
    }
}
