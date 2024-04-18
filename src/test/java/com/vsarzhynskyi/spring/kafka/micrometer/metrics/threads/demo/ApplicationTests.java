package com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;

class ApplicationTests extends BaseIntegrationTest {

	@Test
	void contextLoads() {
	}

	@Test
	void healthCheckIsUp() {
		RestAssured.given()
				.when()
				.port(managementPort)
				.get("/health")
				.prettyPeek()
				.then()
				.statusCode(SC_OK)
				.body("status", is("UP"));
	}

}
