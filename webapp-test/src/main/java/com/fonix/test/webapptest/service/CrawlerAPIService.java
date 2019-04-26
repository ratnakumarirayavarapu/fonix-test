package com.fonix.test.webapptest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fonix.test.webapptest.service.job.CrawlerService;

@Controller
public class CrawlerAPIService {
	
	@Autowired
	CrawlerService service;
	
	@RequestMapping(value = "/api")
	public void start() {
		service.runCrawler();
	}
}
