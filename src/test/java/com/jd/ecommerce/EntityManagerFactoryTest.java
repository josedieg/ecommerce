package com.jd.ecommerce;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.jd.ecommerce.util.ConsoleCor;

public class EntityManagerFactoryTest {
	
    protected static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpBeforClass() {
	entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void tearDownAfterClass() {
	entityManagerFactory.close();
    }

    public static void log(Object obj, Object... args) {
    	ConsoleCor.print(String.format("[LOG " + System.currentTimeMillis() + "] " + obj, args), ConsoleCor.GREEN_BOLD_BRIGHT);
    }

    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}
    }
}
