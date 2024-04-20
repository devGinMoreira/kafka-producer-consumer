# kafka-producer-consumer
Spring boot project for kafka producer consumer


## Setting Up Kafka Producer Consumer Project

1. Start zookeeper
> zookeeper-server-start.bat ..\..\config\zookeeper.properties

2. Start kafka-server
> kafka-server-start.bat ..\..\config\server.properties

3. After producer send a message, we can use CLI to check data
> kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic myTopic