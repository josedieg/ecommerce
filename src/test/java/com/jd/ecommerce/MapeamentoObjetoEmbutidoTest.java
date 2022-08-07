package com.jd.ecommerce;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.enuns.StatusPedido;
import com.jd.ecommerce.model.Endereco;
import com.jd.ecommerce.model.Pedido;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido() {
	
	Endereco endereco = new Endereco();
	endereco.setCep("35.000-000");
	endereco.setCidade("Giran twon");
	endereco.setComplemento("cs");
	endereco.setEstado("Giran");
	
	Pedido pedido = new Pedido();
	pedido.setDataPedido(LocalDateTime.now());
	pedido.setStatus(StatusPedido.AGUARDANDO);
	pedido.setTotal(new BigDecimal(1000));
	pedido.setEnderecoEntrega(endereco);
	
	entityManager.getTransaction().begin();
	entityManager.persist(pedido);
	entityManager.getTransaction().commit();
	entityManager.clear();
	
	Pedido p = entityManager.find(Pedido.class, pedido.getId());
	Assert.assertNotNull(p);
	Assert.assertNotNull(p.getEnderecoEntrega());
	Assert.assertNotNull(p.getEnderecoEntrega().getCep());
    }
}
