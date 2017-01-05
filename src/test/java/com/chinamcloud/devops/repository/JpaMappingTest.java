package com.chinamcloud.devops.repository;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 会遍历所有Entity,执行一次select操作.看entity上的JPA annotation有没有问题,必备.
 * 
 * @author Administrator
 * 
 */
@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class JpaMappingTest {

	private static Logger logger = LoggerFactory.getLogger(JpaMappingTest.class);

	@PersistenceContext
	private EntityManager em;

	@Test
	public void allClassMapping() throws Exception {

		Metamodel model = em.getEntityManagerFactory().getMetamodel();

		assertTrue("No entity mapping found", model.getEntities().size() > 0);

		int i = 0;

		for (EntityType<?> entityType : model.getEntities()) {
			String entityName = entityType.getName();
			em.createQuery("select o from " + entityName + " o").getResultList();
			logger.info("ok: " + entityName);
			i++;
		}

		logger.info("total: " + i);

	}

}
