package com.chinamcloud.devops;

import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResMap {

	private HashMap<String, Object> map = new HashMap<>();

	public HashMap<String, Object> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印Entity信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
