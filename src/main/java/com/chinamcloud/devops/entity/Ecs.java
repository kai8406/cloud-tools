package com.chinamcloud.devops.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ecs {

	protected String code;
	private String computerName;
	protected String description;
	protected Integer id;
	protected Integer tenants;
	private Integer vpc;

	public Ecs() {
	}

	public String getCode() {
		return code;
	}

	public String getComputerName() {
		return computerName;
	}

	public String getDescription() {
		return description;
	}

	public Integer getId() {
		return id;
	}

	public Integer getTenants() {
		return tenants;
	}

	public Integer getVpc() {
		return vpc;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTenants(Integer tenants) {
		this.tenants = tenants;
	}

	public void setVpc(Integer vpc) {
		this.vpc = vpc;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印Entity信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
