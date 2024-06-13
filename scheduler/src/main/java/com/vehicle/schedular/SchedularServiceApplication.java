package com.vehicle.schedular;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vehicle.schedular.repository.ByteArrayRepository;
import com.vehicle.schedular.repository.ByteArrayRepository;
import com.vehicle.schedular.repository.FileProcessingRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.reactive.function.client.WebClient;


@EnableEurekaClient
@EnableKafka
@EnableScheduling
@EnableMongoRepositories(basePackageClasses = ByteArrayRepository.class)
@EnableJpaRepositories (basePackageClasses = FileProcessingRepository.class)
@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class })*/
//@ComponentScan(basePackages = {"com.vehicle.main.*","com.vehicle.main.repository"})
public class SchedularServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedularServiceApplication.class, args);
    }
}
