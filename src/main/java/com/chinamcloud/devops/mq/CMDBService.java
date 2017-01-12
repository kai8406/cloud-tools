package com.chinamcloud.devops.mq;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinamcloud.devops.entity.Ecs;
import com.chinamcloud.devops.entity.Tenants;
import com.chinamcloud.devops.entity.Vpc;
import com.chinamcloud.devops.utils.mapper.BeanMapper;
import com.chinamcloud.devops.utils.mapper.JsonMapper;

@Service
public class CMDBService {

	private static JsonMapper binder = JsonMapper.nonEmptyMapper();

	@Autowired
	protected AmqpService service;

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

	public Ecs findEcs(Integer id) {

		String receiveString = service.convertSendAndReceive("cmopCMDB.ecs.find", id);

		return binder.fromJson(receiveString, Ecs.class);
	}

	public Tenants findTenants(Integer id) {

		String receiveString = service.convertSendAndReceive("cmopCMDB.tenants.find", id);

		return binder.fromJson(receiveString, Tenants.class);
	}

	public Vpc findVpc(Integer id) {

		String receiveString = service.convertSendAndReceive("cmopCMDB.vpc.find", id);

		return binder.fromJson(receiveString, Vpc.class);
	}

	public List<Ecs> getEcs(Map<String, Object> map) {

		String receiveString = service.convertSendAndReceive("cmopCMDB.ecs.findAll", map);

		return coverToList(receiveString, Ecs.class);
	}

	public List<Tenants> getTenants(Map<String, Object> map) {

		String receiveString = service.convertSendAndReceive("cmopCMDB.tenants.findAll", map);

		return coverToList(receiveString, Tenants.class);
	}

	public List<Vpc> getVpc(Map<String, Object> map) {

		String receiveString = service.convertSendAndReceive("cmopCMDB.vpc.findAll", map);

		return coverToList(receiveString, Vpc.class);
	}

}
