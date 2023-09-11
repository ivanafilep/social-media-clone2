package com.example.demo;

import java.sql.Connection;

import javax.persistence.EntityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SocialMediaCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaCloneApplication.class, args);
	}

}
