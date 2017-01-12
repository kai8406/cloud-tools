package com.chinamcloud.devops;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinamcloud.devops.entity.Ecs;
import com.chinamcloud.devops.entity.Tenants;
import com.chinamcloud.devops.entity.Vpc;
import com.chinamcloud.devops.mq.AmqpService;
import com.chinamcloud.devops.utils.mapper.BeanMapper;
import com.chinamcloud.devops.utils.mapper.JsonMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQTest {

	private static JsonMapper binder = JsonMapper.nonEmptyMapper();

	@Autowired
	protected AmqpService service;

	@Test
	public void findTenants() {

		String receiveString = service.convertSendAndReceive("cmopCMDB.tenants.find", 2506);

		Tenants entity = binder.fromJson(receiveString, Tenants.class);

		System.out.println("=============返回的Tenants对象================");
		System.out.println(entity);
		System.out.println("over!");
	}

	@Test
	public void findAllTenants() {

		Map<String, Object> map = new HashMap<>();

		String receiveString = service.convertSendAndReceive("cmopCMDB.tenants.findAll", map);

		List<Tenants> list = coverToList(receiveString, Tenants.class);

		System.out.println("=============返回的Tenants对象================");
		list.stream().forEach(s -> System.out.println(s));
		System.out.println("over!");
	}

	@Test
	public void findAllVPC() {

		Map<String, Object> map = new HashMap<>();
		map.put("EQ_tenants", 2506);

		String receiveString = service.convertSendAndReceive("cmopCMDB.vpc.findAll", map);

		List<Vpc> list = coverToList(receiveString, Vpc.class);

		System.out.println("=============返回的Vpc对象================");
		list.stream().forEach(s -> System.out.println(s));
		System.out.println("over!");
	}

	@Test
	public void findAllEcs() {

		Map<String, Object> map = new HashMap<>();
		map.put("EQ_vpc", 7044);

		String receiveString = service.convertSendAndReceive("cmopCMDB.ecs.findAll", map);

		List<Ecs> list = coverToList(receiveString, Ecs.class);

		System.out.println("=============返回的Ecs对象================");
		list.stream().forEach(s -> System.out.println(s));
		System.out.println("over!");
	}

	/**
	 * 将解析出来的字符串转换为指定的T.
	 * 
	 * @param receiveString
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> coverToList(String receiveString, Class<T> clazz) {

		List<Object> list = binder.fromJson(receiveString, List.class);

		return BeanMapper.mapList(list, clazz);
	}

}
