package com.chinamcloud.devops;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinamcloud.devops.entity.ExecuteLog;
import com.chinamcloud.devops.service.db.ExecuteLogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExecuteLogTest {

	@Autowired
	public ExecuteLogService service;

	@Test
	public void delete() {
		service.delete(1);
	}

	@Test
	public void find() {

		Map<String, Object> map = new HashMap<>();
		map.put("EQ_id", 1);
		ExecuteLog entity = service.find(map);

		System.out.println(entity);
	}

	@Test
	public void findOne() {
		System.out.println(service.find(1));
	}

	@Test
	public void list() {

		Map<String, Object> map = new HashMap<>();
		List<ExecuteLog> list = service.findAll(map);

		list.stream().forEach(s -> System.out.println(s));
	}

	@Test
	public void pageable() {

		Map<String, Object> map = new HashMap<>();

		Pageable pageable = new PageRequest(0, 6, new Sort(Direction.DESC, "id"));

		Page<ExecuteLog> page = service.findAll(map, pageable);

		page.getContent().stream().forEach(s -> System.err.println("Content:" + s));

		
		System.err.println("hasContent:" + page.hasContent()); //是否包含内容
		System.err.println("Number:" + page.getNumber());  //当前页为第x页
		System.err.println("NumberOfElements:" + page.getNumberOfElements()); //当前页显示的数据数量
		System.err.println("Size:" + page.getSize());//每页大小为15
		System.err.println("TotalElements:" + page.getTotalElements()); //总数据量
		System.err.println("TotalPages:" + page.getTotalPages()); //总页数
		System.err.println("hasPrevious:" + page.hasPrevious());//是否有上页
		System.err.println("hasNext:" + page.hasNext());//是否有下页
		System.err.println("isFirst:" + page.isFirst());//是否第一页
		System.err.println("isLast:" + page.isLast());//是否最后一页

	}

	@Test
	public void save() {

		for (int i = 1; i < 100; i++) {
			
		
		ExecuteLog entity = new ExecuteLog();
		entity.setCreateTime(new Date());
		entity.setInstance("instance");
		entity.setCommand("command");
		entity.setResult("result");

		System.out.println(service.saveAndFlush(entity));
		}
	}

	@Test
	public void update() {

		ExecuteLog entity = service.find(1);
		entity.setCommand("commandupdate");

		System.out.println(service.saveAndFlush(entity));
	}

}
