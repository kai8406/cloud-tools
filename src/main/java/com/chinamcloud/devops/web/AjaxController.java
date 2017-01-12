package com.chinamcloud.devops.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.devops.entity.Ecs;
import com.chinamcloud.devops.entity.Vpc;
import com.chinamcloud.devops.mq.CMDBService;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private CMDBService Service;

	@GetMapping("ecs/vpc/{vpcId}")
	public List<Ecs> getEcs(@PathVariable(value = "vpcId") Integer vpcId) {
		Map<String, Object> map = new HashMap<>();
		map.put("EQ_vpc", vpcId);
		return Service.getEcs(map);
	}

	@GetMapping("vpc/tenants/{tenantsId}")
	public List<Vpc> getVpc(@PathVariable(value = "tenantsId") Integer tenantsId) {
		Map<String, Object> map = new HashMap<>();
		map.put("EQ_tenants", tenantsId);
		return Service.getVpc(map);
	}

}
