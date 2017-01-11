package com.chinamcloud.devops.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmqpService {

	@Autowired
	public AmqpTemplate amqpTemplate;

	/**
	 * 向Rabbit MQ服务器发送对象.(异步)
	 * 
	 * @param routingKey
	 * @param obj
	 * @return
	 */
	public void convertAndSend(String routingKey, Object obj) {
		amqpTemplate.convertAndSend(routingKey, obj);
	}

	/**
	 * 向Rabbit MQ服务器发送对象.(异步)
	 * 
	 * @param exchangeName
	 * @param routingKey
	 * @param obj
	 */
	public void convertAndSend(String exchangeName, String routingKey, Object obj) {
		amqpTemplate.convertAndSend(exchangeName, routingKey, obj);
	}

	/**
	 * 向Rabbit MQ服务器发送对象,并等待返回参数.(同步,RPC模式)
	 * 
	 * @param routingKey
	 * @param obj
	 * @return
	 */
	public String convertSendAndReceive(String routingKey, Object obj) {
		return (String) amqpTemplate.convertSendAndReceive(routingKey, obj);
	}

}
