package io.conduktor.demos.kafkabasics;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProductDemo {

    private static final Logger log = LoggerFactory.getLogger(ProductDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Starting product demo");

        // create Producer Properties
        Properties properties = new Properties();

        // connect to Localhost
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        // set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a Producer Record
        ProducerRecord<String, String> producerRecord
                = new ProducerRecord<>("demo_java", "hello world");

        // send data -- async
        producer.send(producerRecord);

        // tell the producer to send all data and block until done -- sync
        producer.flush();

        // flush and close the producer
        producer.close();

    }
}
