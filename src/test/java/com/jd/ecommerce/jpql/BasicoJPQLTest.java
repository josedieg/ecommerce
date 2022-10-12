package com.jd.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.dto.ProdutoDTO;
import com.jd.ecommerce.model.Pedido;

public class BasicoJPQLTest extends EntityManagerTest {

	@Test
	public void projetarResultado() {
		String jpql = "select id, nome from Produto p";
		TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> list = query.getResultList();
		Assert.assertTrue(list.get(0).length == 2);
	}

	@Test
	public void selecionarAtributo() {
		String jpql = "select p.nome from Produto p";
		TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
		List<String> list = query.getResultList();
		Assert.assertTrue(String.class.equals(list.get(0).getClass()));

	}

	@Test
	public void projetarNoDTO() {
		String jpql = "select new com.jd.ecommerce.dto.ProdutoDTO(id, nome) from Produto";
		TypedQuery<ProdutoDTO> query = entityManager.createQuery(jpql, ProdutoDTO.class);
		List<ProdutoDTO> list = query.getResultList();
		Assert.assertFalse(list.isEmpty());

	}

	@Test
	public void buscarPorIdentificador() {
		TypedQuery<Pedido> query = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);
		Pedido pedido = query.getSingleResult();

		Assert.assertNotNull(pedido);
	}

}
