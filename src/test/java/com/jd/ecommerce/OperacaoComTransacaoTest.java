package com.jd.ecommerce;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.model.Produto;

public class OperacaoComTransacaoTest extends EntityManagerTest {
    
  @Test
  public void impedirOperacaoComBancoDeDados() {
	Produto p = entityManager.find(Produto.class, 1);
	
	entityManager.detach(p);
	entityManager.getTransaction().begin();
	p.setNome("Kindle 11 geracao Paperwhite");
	entityManager.getTransaction().commit();
	entityManager.clear();

	Produto produto = entityManager.find(Produto.class, p.getId());
	Assert.assertNotEquals(produto.getNome(), "Kindle 11 geracao Paperwhite");
    }
    
//    @Test
//    public void mostrarDiferencaPersistEMerge() {
//	Produto pPersist = new Produto();
//
//	pPersist.setId(5);
//	pPersist.setNome("Smartphone One");
//	pPersist.setDescricao("O melhor telefone.");
//	pPersist.setPreco(new BigDecimal(2500));
//
//	entityManager.getTransaction().begin();
//	entityManager.persist(pPersist);
//	pPersist.setNome("Smartphone One 2");
//	entityManager.getTransaction().commit();
//	entityManager.clear();
//
//	Produto produto = entityManager.find(Produto.class, pPersist.getId());
//	Assert.assertNotNull(produto);
//	
//	Produto pMerge = new Produto();
//
//	pMerge.setId(6);
//	pMerge.setNome("Notebook One");
//	pMerge.setDescricao("O melhor Notebook.");
//	pMerge.setPreco(new BigDecimal(2500));
//
//	entityManager.getTransaction().begin();
//	entityManager.merge(pMerge);
//	pMerge.setNome("Notebook One 2");
//	entityManager.getTransaction().commit();
//	entityManager.clear();
//
//	Produto produtoMerge = entityManager.find(Produto.class, pMerge.getId());
//	Assert.assertNotNull(produtoMerge);
//    } 
//
//    @Test
//    public void inserirPrimeiroObjetoComMerge() {
//	Produto p = new Produto();
//
//	p.setId(4);
//	p.setNome("Microfone Zoom");
//	p.setDescricao("A melhor definicao Audio.");
//	p.setPreco(new BigDecimal(2000));
//
//	entityManager.getTransaction().begin();
//	entityManager.merge(p);
//	entityManager.getTransaction().commit();
//	entityManager.clear();
//
//	Produto produto = entityManager.find(Produto.class, p.getId());
//	Assert.assertNotNull(produto);
//    }
//
//    @Test
//    public void atualizarObjetoGerenciado() {
//	Produto p = entityManager.find(Produto.class, 1);
//	p.setNome("Kindle 11 geracao Paperwhite");
//
//	entityManager.getTransaction().begin();
//	entityManager.getTransaction().commit();
//	entityManager.clear();
//
//	Produto produto = entityManager.find(Produto.class, p.getId());
//	Assert.assertEquals(produto.getNome(), "Kindle 11 geracao Paperwhite");
//	
//    }
//    
//    @Test
//    public void atualizarObjeto() {
//	Produto p = new Produto();
//	p.setId(1);
//	p.setNome("Kindle Paperwhite");
//	p.setDescricao("Conhecendo o novo kindle");
//	p.setPreco(new BigDecimal(1000));
//
//	entityManager.getTransaction().begin();
//	entityManager.merge(p);
//	entityManager.getTransaction().commit();
//	entityManager.clear();
//
//	Produto produto = entityManager.find(Produto.class, p.getId());
//	Assert.assertNotNull(produto);
//	Assert.assertEquals(produto.getNome(), "Kindle Paperwhite");
//	
//    }
//
//    @Test 
//    public void removerObjeto() {
//	Produto p = entityManager.find(Produto.class, 3);
//
//	entityManager.getTransaction().begin();
//	entityManager.remove(p);
//	entityManager.getTransaction().commit();
//
//	Produto produto = entityManager.find(Produto.class, p.getId());
//	Assert.assertNull(produto);
//    }
//
//    @Test
//    public void abrirEFecharTransacao() {
//	entityManager.getTransaction().begin();
//	entityManager.getTransaction().commit();
//    }
//
//    @Test
//    public void inserirPrimeiroObjeto() {
//	Produto p = new Produto();
//
//	p.setId(2);
//	p.setNome("Camera Canon");
//	p.setDescricao("A melhor definicao para suas fotos.");
//	p.setPreco(new BigDecimal(5000));
//
//	entityManager.getTransaction().begin();
//	entityManager.persist(p);
//	entityManager.getTransaction().commit();
//	entityManager.clear();
//
//	Produto produto = entityManager.find(Produto.class, p.getId());
//	Assert.assertNotNull(produto);
//    }
}
