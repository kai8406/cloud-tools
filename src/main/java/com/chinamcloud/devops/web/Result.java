package com.chinamcloud.devops.web;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Result {

	private String resp_info;
	private String resultMessage;
	private String taskCode;
	private Integer taskStatus;

	public Result() {
	}

	public String getResp_info() {
		return resp_info;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setResp_info(String resp_info) {
		this.resp_info = resp_info;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	

	/**
	 * 重新实现toString()函数方便在日志中打印Entity信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
