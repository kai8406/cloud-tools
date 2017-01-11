package com.chinamcloud.devops.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Vpc {

	private String cidr;
	protected String code;
	protected String description;
	protected Integer id;
	protected Integer tenants;

	public Vpc() {
	}

	public String getCidr() {
		return cidr;
	}

	public String getCode() {
		return code;
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

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public void setCode(String code) {
		this.code = code;
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

	/**
	 * 重新实现toString()函数方便在日志中打印Entity信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
