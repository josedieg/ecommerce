package com.jd.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jd.ecommerce.model.Produto;

public class EntityManagerTest {

    protected static EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    @BeforeClass
    public static void setUpBeforClass() {
	entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void tearDownAfterClass() {
	entityManagerFactory.close();
    }

    @Before
    public void setUp() {
	entityManager = entityManagerFactory.createEntityManager();
    }
}
