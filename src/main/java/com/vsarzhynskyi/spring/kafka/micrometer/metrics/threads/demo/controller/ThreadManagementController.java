package com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo.controller;

import com.vsarzhynskyi.spring.kafka.micrometer.metrics.threads.demo.model.ThreadDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@RestController
public class ThreadManagementController {

  @GetMapping("/threads")
  public ThreadDetailsResponse getThreads(@RequestParam(name = "name-prefix", required = false) String threadNamePrefix) {
    var threadNames = Thread.getAllStackTraces().keySet().stream()
        .map(Thread::getName)
        .filter(threadName -> isNull(threadNamePrefix) || threadName.startsWith(threadNamePrefix))
        .sorted()
        .collect(toList());

    return ThreadDetailsResponse.builder()
        .threadNames(threadNames)
        .build();
  }

}
