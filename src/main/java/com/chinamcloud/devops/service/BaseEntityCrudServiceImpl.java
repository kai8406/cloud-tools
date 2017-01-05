package com.chinamcloud.devops.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.chinamcloud.devops.repository.JpaSpecificationRepository;
import com.chinamcloud.devops.utils.persistence.DynamicSpecifications;
import com.chinamcloud.devops.utils.persistence.SearchFilter;

public abstract class BaseEntityCrudServiceImpl<T, R extends JpaSpecificationRepository<T>>
		implements BaseEntityCrudService<T> {

	@SuppressWarnings("unchecked")
	private Specification<T> buildSpecification(Map<String, Object> searchParams) {

		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		/**
		 * 通过反射的API来获取T的Class
		 * 
		 * http://www.blogjava.net/calvin/archive/2009/12/10/43830.html
		 */
		return DynamicSpecifications.bySearchFilter(filters.values(),
				(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	public long count(Map<String, Object> searchParams) {
		return getRepository().count(buildSpecification(searchParams));
	}

	@Override
	public void delete(Integer id) {
		getRepository().delete(id);
	}

	@Override
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		getRepository().deleteInBatch(entities);
	}

	@Override
	public boolean exists(Integer id) {
		return getRepository().exists(id);
	}

	@Override
	public T find(Integer id) {
		return getRepository().findOne(id);
	}

	@Override
	public T find(Map<String, Object> searchParams) {
		return getRepository().findOne(buildSpecification(searchParams));
	}

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<T> findAll(Map<String, Object> searchParams) {
		return getRepository().findAll(buildSpecification(searchParams));
	}

	@Override
	public Page<T> findAll(Map<String, Object> searchParams, Pageable pageable) {
		return getRepository().findAll(buildSpecification(searchParams), pageable);
	}

	protected abstract R getRepository();

	@Override
	public List<T> save(Iterable<T> entities) {
		return getRepository().save(entities);
	}

	@Override
	public T saveAndFlush(T entity) {
		return getRepository().saveAndFlush(entity);
	}

}
