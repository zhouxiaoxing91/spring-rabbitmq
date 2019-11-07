package com.dingcheng.confirms.publish;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deadLetterPublishService")
public class DeadLetterPublishService {
	@Autowired
	private RabbitTemplate rmqpTemplate;
    
    public void send(String routingKey, Object message) {  
    	rmqpTemplate.convertAndSend(routingKey, message);
    }

	public void send(String exchange, String routingKey, Object message) {
		rmqpTemplate.convertAndSend(exchange, routingKey, message);
	}
}
