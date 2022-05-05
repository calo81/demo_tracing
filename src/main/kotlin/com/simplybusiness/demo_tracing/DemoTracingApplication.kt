package com.simplybusiness.demo_tracing

import org.apache.kafka.streams.kstream.KStream
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.sleuth.Tracer
import org.springframework.context.annotation.Bean
import java.util.function.Function


@SpringBootApplication
@ConfigurationPropertiesScan
class Beans {
    private val logger: Logger = LoggerFactory.getLogger(Beans::class.java)

	@Bean
    fun consumeAndProduce(): Function<KStream<String, String>, KStream<String, String>>? {
        return Function<KStream<String, String>, KStream<String, String>> { input ->
            input.foreach { key, value ->  logger.info("tracer ")}
            input
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Beans>(*args)
}
