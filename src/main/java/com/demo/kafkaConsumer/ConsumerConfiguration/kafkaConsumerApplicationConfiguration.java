package com.demo.kafkaConsumer.ConsumerConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class kafkaConsumerApplicationConfiguration {

	@Value("${groupid}")
	private String groupId;
	
	private Map<String,Object> consumerProps(){
		Map<String,Object> props= new HashMap<String, Object>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,  groupId);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return props;	
	}
	
	@Bean
	public ConsumerFactory<String, String> kafkaConsumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerProps());
	}
	
	@Bean
	ConcurrentKafkaListenerContainerFactory<String,String> kafkaListerFactory(ConsumerFactory<String, String> kafkaConsumerFactory){
		ConcurrentKafkaListenerContainerFactory<String, String> kafkaListemer= new ConcurrentKafkaListenerContainerFactory<>();
		kafkaListemer.setConsumerFactory(kafkaConsumerFactory);
		return kafkaListemer;
	}
}
