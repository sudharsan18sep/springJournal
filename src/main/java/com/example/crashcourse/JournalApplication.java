package com.example.crashcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//restapi - representational state transfer http
//GET - read
//post - create
//put - update
//delete
//only on the main class only one per applicaiton
//does three jobs
// @configuration(the class will provide some configuration, can create beans inside the class ) @EnableAutoConfiguration @componentScan(search for component ie beans)
//componentScan will only scan wihting the base package
@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

}
