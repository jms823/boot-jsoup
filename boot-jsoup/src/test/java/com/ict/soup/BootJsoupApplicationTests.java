package com.ict.soup;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ict.soup.collection.DaumIssue;
import com.ict.soup.repository.DaumIssueRepository;

import lombok.extern.slf4j.Slf4j;



@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BootJsoupApplicationTests {
	
	static final String CONNECT_URI = "https://media.daum.net/culture/";
	static final String SELECT_QUERY = "ul.list_ranking";
		
	@Autowired
	private DaumIssueRepository diRepo;
	

	@Test
	public void contextLoads() throws IOException {
		Document doc = Jsoup.connect(CONNECT_URI).get();
		Elements ulElements = doc.select(SELECT_QUERY);
		assertEquals(1, ulElements.size());
	}
	
	
	@Test
	public void selectList() throws IOException {
		Document doc = Jsoup.connect(CONNECT_URI).get();
		Elements ulElements = doc.select(SELECT_QUERY);
		Elements aElements = ulElements.select("li>strong>a");
		List<DaumIssue> diList = new ArrayList<DaumIssue>();
		
		assertEquals(10, aElements.size());
		for(Element aElement : aElements) {
			String uri = aElement.attr("href");
			String text = aElement.text();
			
			DaumIssue di = new DaumIssue();
			di.setUri(uri);
			di.setText(text);
			diList.add(di);
		}
		
		diRepo.saveAll(diList);
		log.info("diList=>{}",diList);
	}
	
	

}
