package com.demo.kafkaConsumer.messageProcessing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {
	
	
	@KafkaListener(topics = "quickstart-events" ,groupId = "quickstart-events")
	public void messageProcessing(String value) {
		System.out.println("value from Topic "+value);
	}
	
}
