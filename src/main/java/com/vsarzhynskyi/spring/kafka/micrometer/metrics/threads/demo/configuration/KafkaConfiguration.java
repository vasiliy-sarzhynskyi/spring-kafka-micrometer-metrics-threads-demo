package com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableKafka
public class KafkaConfiguration {

  private static final String CONSUMER_MONITORING_THREAD_NAME_PREFIX = "custom-consumer-monitoring-scheduler-";

  @Bean
  public TaskScheduler consumerMonitoringTaskScheduler(@Value("${app.kafka.monitoring.pool-size}") int poolSize) {
    var threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setThreadNamePrefix(CONSUMER_MONITORING_THREAD_NAME_PREFIX);
    threadPoolTaskScheduler.setPoolSize(poolSize);
    return threadPoolTaskScheduler;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(
      ConsumerFactory<String, String> consumerFactory,
      TaskScheduler consumerMonitoringTaskScheduler,
      @Value("${app.kafka.listener.concurrency}") int consumerConcurrency) {
    var containerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    containerFactory.setConsumerFactory(consumerFactory);
    containerFactory.getContainerProperties().setScheduler(consumerMonitoringTaskScheduler);
    containerFactory.setConcurrency(consumerConcurrency);
    return containerFactory;
  }

}
