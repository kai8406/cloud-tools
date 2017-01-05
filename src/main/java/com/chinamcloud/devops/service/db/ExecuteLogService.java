package com.chinamcloud.devops.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinamcloud.devops.entity.ExecuteLog;
import com.chinamcloud.devops.repository.db.ExecuteLogRepository;
import com.chinamcloud.devops.service.BaseEntityCrudServiceImpl;

@Service
@Transactional
public class ExecuteLogService extends BaseEntityCrudServiceImpl<ExecuteLog, ExecuteLogRepository> {

	@Autowired
	private ExecuteLogRepository repository;

	@Override
	protected ExecuteLogRepository getRepository() {
		return repository;
	}

}
