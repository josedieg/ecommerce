package com.jd.ecommerce.jpql;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.StatusPagamento;
import com.jd.ecommerce.model.NotaFiscal;
import com.jd.ecommerce.model.Pedido;


public class PassandoParametrosTest extends EntityManagerTest {
	
	
    @Test
    public void passarParametroDate() {
        String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";

        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

        List<NotaFiscal> lista = typedQuery.getResultList();
        Assert.assertTrue(lista.size() == 1);
    }
    
	@Test
	public void passandoParametroPorNome() {
		String jpql = "select p from Pedido p join p.pagamento pag where p.id = :pedidoId "
				+ "and pag.status = :pagamentoStatus";

		TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
		query.setParameter("pedidoId", 2);
		query.setParameter("pagamentoStatus", StatusPagamento.PROCESSANDO);
		
		List<Pedido> list = query.getResultList();
		Assert.assertTrue(list.size() == 1);
	}
	
	@Test
	public void passandoParametroPorPosicao() {
		String jpql = "select p from Pedido p join p.pagamento pag where p.id = ?1 "
				+ "and pag.status = ?2";

		TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
		query.setParameter(1, 2);
		query.setParameter(2, StatusPagamento.PROCESSANDO);
		
		List<Pedido> list = query.getResultList();
		Assert.assertTrue(list.size() == 1);
	}
}
