package com.ordersystem.ms_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MsNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificationsApplication.class, args);
	}

}
