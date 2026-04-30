package com.example.crashcourse;

import com.mongodb.client.MongoDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement //search for mehtods that has transactional annotation
//will create a transcational context
//achieve atomicity and Isolation
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	//using the inbuilt @configuration annotation we can create a bean
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
				//MongoDatabaseFactory helps in creating connection with mongobdb
		//t enables ACID transactions in MongoDB (only supported in replica sets).
		//@Transactional → Spring fetches it automatically
	}

}
