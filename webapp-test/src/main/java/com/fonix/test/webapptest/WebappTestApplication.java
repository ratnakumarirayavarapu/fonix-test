package com.fonix.test.webapptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fonix.test.webapptest.service.job.CrawlerService;


@SpringBootApplication
@EnableScheduling
public class WebappTestApplication implements CommandLineRunner{
	
	@Autowired
	CrawlerService crawler;
	
	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(WebappTestApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		//crawler.runCrawler();
	}	
	
}
