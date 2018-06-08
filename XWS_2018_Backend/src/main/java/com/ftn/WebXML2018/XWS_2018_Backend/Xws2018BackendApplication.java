package com.ftn.WebXML2018.XWS_2018_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Xws2018BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Xws2018BackendApplication.class, args);
	}
}
