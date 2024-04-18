package com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {
        SpringKafkaMicrometerMetricsThreadsDemoApplication.class
    })
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 10, topics = {"demo-topic-1", "demo-topic-2", "demo-topic-3", "demo-topic-4", "demo-topic-5"})
public abstract class BaseIntegrationTest {

  @LocalServerPort
  protected int serverPort;

  @LocalManagementPort
  protected int managementPort;

}
