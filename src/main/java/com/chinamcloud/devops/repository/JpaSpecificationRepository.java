package com.chinamcloud.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * spring-data-jpa基类
 * 
 * @author Sobey
 *
 * @param <T>
 */
@NoRepositoryBean
// 该注释在1.7实现,表示该接口不能当做一个Repository接口
public interface JpaSpecificationRepository<T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

}
