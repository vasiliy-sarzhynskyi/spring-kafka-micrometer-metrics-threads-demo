package com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

  @KafkaListener(
      id = "kafka-listener-${app.kafka.consumer.topics.topic-1}",
      topics = "${app.kafka.consumer.topics.topic-1}",
      idIsGroup = false,
      containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void listenTopic1(ConsumerRecord<String, String> consumerRecord) {
    log.info("handling consumed message key '{}' and value '{}' from topic '{}'",
        consumerRecord.key(), consumerRecord.value(), consumerRecord.topic());
  }

  @KafkaListener(
      id = "kafka-listener-${app.kafka.consumer.topics.topic-2}",
      topics = "${app.kafka.consumer.topics.topic-2}",
      idIsGroup = false,
      containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void listenTopic2(ConsumerRecord<String, String> consumerRecord) {
    log.info("handling consumed message key '{}' and value '{}' from topic '{}'",
        consumerRecord.key(), consumerRecord.value(), consumerRecord.topic());
  }

  @KafkaListener(
      id = "kafka-listener-${app.kafka.consumer.topics.topic-3}",
      topics = "${app.kafka.consumer.topics.topic-3}",
      idIsGroup = false,
      containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void listenTopic3(ConsumerRecord<String, String> consumerRecord) {
    log.info("handling consumed message key '{}' and value '{}' from topic '{}'",
        consumerRecord.key(), consumerRecord.value(), consumerRecord.topic());
  }

  @KafkaListener(
      id = "kafka-listener-${app.kafka.consumer.topics.topic-4}",
      topics = "${app.kafka.consumer.topics.topic-4}",
      idIsGroup = false,
      containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void listenTopic4(ConsumerRecord<String, String> consumerRecord) {
    log.info("handling consumed message key '{}' and value '{}' from topic '{}'",
        consumerRecord.key(), consumerRecord.value(), consumerRecord.topic());
  }

  @KafkaListener(
      id = "kafka-listener-${app.kafka.consumer.topics.topic-5}",
      topics = "${app.kafka.consumer.topics.topic-5}",
      idIsGroup = false,
      containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void listenTopic5(ConsumerRecord<String, String> consumerRecord) {
    log.info("handling consumed message key '{}' and value '{}' from topic '{}'",
        consumerRecord.key(), consumerRecord.value(), consumerRecord.topic());
  }

}
