package com.jd.ecommerce.mapavancado;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.SexoCliente;
import com.jd.ecommerce.enuns.StatusPagamento;
import com.jd.ecommerce.model.Cliente;
import com.jd.ecommerce.model.Pagamento;
import com.jd.ecommerce.model.PagamentoCartao;
import com.jd.ecommerce.model.Pedido;

public class HerancaTest extends EntityManagerTest {

    @Test
    public void salvarCliente() {
	Cliente cliente = new Cliente();
	cliente.setNome("Julieta");
	cliente.setSexo(SexoCliente.MASCULINO);

	entityManager.getTransaction().begin();
	entityManager.persist(cliente);
	entityManager.getTransaction().commit();

	entityManager.clear();

	Cliente c = entityManager.find(Cliente.class, cliente.getId());
	Assert.assertNotNull(c.getId());
    }
    

    @Test
    public void incluirPagamentoPedido() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setPedido(pedido);
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getPagamento());
    }

    @Test
    public void buscarPagamentos() {
	List<Pagamento> pagamentos = entityManager
		.createQuery("select p from Pagamento p")
		.getResultList();

	Assert.assertFalse(pagamentos.isEmpty());
    }

}
