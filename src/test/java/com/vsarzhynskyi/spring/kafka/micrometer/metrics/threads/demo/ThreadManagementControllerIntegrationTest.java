package com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo;

import com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo.model.ThreadDetailsResponse;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ThreadManagementControllerIntegrationTest extends BaseIntegrationTest {

  @Test
  void testFetchAllThreads() {
    var fetchedThreadNames = RestAssured.given()
        .when()
        .port(serverPort)
        .get("/threads")
        .then()
        .statusCode(SC_OK)
        .extract()
        .body()
        .as(ThreadDetailsResponse.class);

    assertThat(fetchedThreadNames.getThreadNames()).isNotEmpty();
    log.info("fetchedThreadNames = {}", fetchedThreadNames);
  }

  @Test
  void testFetchAndVerifyKafkaConsumerRelatedThreads() {
    var fetchedThreadNames = RestAssured.given()
        .when()
        .port(serverPort)
        .get("/threads")
        .then()
        .statusCode(SC_OK)
        .extract()
        .body()
        .as(ThreadDetailsResponse.class);

    var threadNames = fetchedThreadNames.getThreadNames();
    assertThat(threadNames).isNotEmpty();
    log.info("fetchedThreadNames = {}", fetchedThreadNames);

    var kafkaCoordinatorHeartbeatThreadNames = threadNames.stream()
        .filter(name -> name.startsWith("kafka-coordinator-heartbeat-thread"))
        .toList();
    var kafkaListenerThreadNames = threadNames.stream()
        .filter(name -> name.startsWith("kafka-listener-demo-topic"))
        .toList();
    var micrometerMetricsThreadNames = threadNames.stream()
        .filter(name -> name.startsWith("micrometer-kafka-metrics"))
        .toList();
    var customSpringConsumerMonitoringThreadNames = threadNames.stream()
        .filter(name -> name.startsWith("custom-consumer-monitoring-scheduler"))
        .toList();

    assertThat(kafkaCoordinatorHeartbeatThreadNames).hasSize(50);
    assertThat(kafkaListenerThreadNames).hasSize(50);
    assertThat(micrometerMetricsThreadNames).hasSize(50);
    assertThat(customSpringConsumerMonitoringThreadNames).hasSize(4);
  }

  @Test
  void testFetchSpringKafkaMicrometerMetricsThreads() {
    // expectation based on current implementation inside io.micrometer.core.instrument.binder.kafka.KafkaMetrics
    // <listener_concurrency> * <number_of_kafka_listeners> = 10 * 5 = 50
    int expectedMicrometerMetricsThreads = 50;
    var fetchedThreadNames = RestAssured.given()
        .when()
        .port(serverPort)
        .get("/threads?name-prefix=micrometer-kafka-metrics")
        .then()
        .statusCode(SC_OK)
        .extract()
        .body()
        .as(ThreadDetailsResponse.class);

    assertThat(fetchedThreadNames.getThreadNames()).isNotEmpty();
    assertThat(fetchedThreadNames.getThreadNames()).hasSize(expectedMicrometerMetricsThreads);
    log.info("fetchedThreadNames = {}", fetchedThreadNames);
  }

}
