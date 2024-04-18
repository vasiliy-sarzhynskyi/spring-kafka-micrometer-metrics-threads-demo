<b>Demo project to show Kafka consumer related threads from Spring-Kafka and Micrometer metrics</b>

## Functionality
- Endpoint `GET /threads` that returns a list of application thread names
- Integration tests for demo

## Launch integration tests
It uses an embedded Kafka broker (as an alternative, we could use test containers with Kafka docker image). 

Integration test [ThreadManagementControllerIntegrationTest](src/test/java/com/vsarzhynskyi/spring/kafka/micrometer/metrics/threads/demo/ThreadManagementControllerIntegrationTest.java) shows which threads application created related to Kafka consumers.

## Launch locally
To launch application, make sure that you have a running Kafka broker on your machine, or you need to update the configuration property `bootstrap-servers` from
`application.yml`. Also, topics `demo-topic-1`, `demo-topic-2`, `demo-topic-3`, `demo-topic-4`, `demo-topic-5` should be created in advance.

To install, launch and stop Kafka broker on your Mac, you could use the following `Homebrew` commands: 

install:
- `brew install kafka`

start:
- `brew services start zookeeper`
- `brew services start kafka`

stop:
- `brew services stop kafka`
- `brew services stop zookeeper`

