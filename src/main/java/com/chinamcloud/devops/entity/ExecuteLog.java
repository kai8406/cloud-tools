package com.chinamcloud.devops.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "execute_log")
public class ExecuteLog {

	@Column(name = "command")
	private String command;

	@Column(name = "create_time")
	private Date createTime;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "instance_code")
	private String instanceCode;

	@Column(name = "result")
	private String result;

	@Column(name = "vpc_code")
	private String vpcCode;

	public ExecuteLog() {
	}

	public String getCommand() {
		return command;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public String getInstanceCode() {
		return instanceCode;
	}

	public String getResult() {
		return result;
	}

	public String getVpcCode() {
		return vpcCode;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInstanceCode(String instanceCode) {
		this.instanceCode = instanceCode;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setVpcCode(String vpcCode) {
		this.vpcCode = vpcCode;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印Entity信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
