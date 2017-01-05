package com.chinamcloud.devops.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

	@PersistenceContext
	private EntityManager em;

}
