package com.jd.ecommerce.multitenant;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.jd.ecommerce.EntityManagerFactoryTest;
import com.jd.ecommerce.model.Produto;
import com.jd.ecommerce.multitenant.hibernate.EcmCurrentTenantIdentifierResolver;

public class MultiTenantTest extends EntityManagerFactoryTest {

    @Test
    public void abordagemPorSchema() {
        EcmCurrentTenantIdentifierResolver.setTenantIdentifier("db_ecommerce_loja1");
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        Produto produto1 = entityManager1.find(Produto.class, 1);
        Assert.assertEquals("smartphone 5G", produto1.getNome());
        entityManager1.close();

        EcmCurrentTenantIdentifierResolver.setTenantIdentifier("db_ecommerce");
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        Produto produto2 = entityManager2.find(Produto.class, 1);
        Assert.assertEquals("smartphone 6G", produto2.getNome());
        entityManager2.close();
    }
}
