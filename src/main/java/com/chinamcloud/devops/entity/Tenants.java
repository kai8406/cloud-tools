package com.chinamcloud.devops.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Tenants {

	private String accessKey;
	protected String code;
	private String company;
	protected String description;
	private String email;
	protected Integer id;
	private String phone;
	private Integer tenantsType;

	public Tenants() {
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getCode() {
		return code;
	}

	public String getCompany() {
		return company;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public String getPhone() {
		return phone;
	}

	public Integer getTenantsType() {
		return tenantsType;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setTenantsType(Integer tenantsType) {
		this.tenantsType = tenantsType;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印Entity信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
