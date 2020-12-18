package com.dangtuan.delivery.config.kafka;

import com.dangtuan.kafka.KafkaMessage;
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
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Value(value = "${kafka.delivery.topic.group}")
  private String groupId;

  public ConsumerFactory<String, KafkaMessage> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
        new JsonDeserializer<>(KafkaMessage.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
