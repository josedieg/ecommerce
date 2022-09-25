package com.jd.ecommerce.mapavancado;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerTest;
import com.jd.ecommerce.enuns.SexoCliente;
import com.jd.ecommerce.model.Cliente;

public class SecondaryTableTest extends EntityManagerTest{

    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Diego");
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(2000, 1, 1));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente c = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(c.getSexo());
    }
}
