package com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class ThreadDetailsResponse {

  List<String> threadNames;

}
