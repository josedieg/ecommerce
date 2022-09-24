package com.jd.ecommerce.mapavancado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.model.NotaFiscal;
import com.jd.ecommerce.model.Pedido;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvarXmlNota() {
	Pedido pedido = entityManager.find(Pedido.class, 1);

	NotaFiscal notaFiscal = new NotaFiscal();
	notaFiscal.setPedido(pedido);
	notaFiscal.setDataEmissao(new Date());
	notaFiscal.setXml(carregarNotaFiscal());

	entityManager.getTransaction().begin();
	entityManager.persist(notaFiscal);
	entityManager.getTransaction().commit();

	NotaFiscal nf = entityManager.find(NotaFiscal.class, notaFiscal.getId());
	Assert.assertNotNull(nf.getXml());
	Assert.assertTrue(nf.getXml().length > 0);

    }

    private static byte[] carregarNotaFiscal() {
	try {
	    return SalvandoArquivosTest.class.getResourceAsStream(
		    "/nota-fiscal.xml").readAllBytes();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
}
