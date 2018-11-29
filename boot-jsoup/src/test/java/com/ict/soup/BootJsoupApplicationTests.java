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

import com.ict.soup.collection.NaverITNews;
import com.ict.soup.repository.NaverITNewsRepository;

import lombok.extern.slf4j.Slf4j;



@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BootJsoupApplicationTests {
	
	static final String CONNECT_URI = "https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid1=105&sid2=230";
	static final String SELECT_QUERY = "ul.type06_headline";
	
	@Autowired
	private NaverITNewsRepository nitnRepo;
	

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
		Elements aElements = ulElements.select("li>dl>dt:not(.photo)>a");
		List<NaverITNews> nitnList = new ArrayList<NaverITNews>();
		
		assertEquals(10, aElements.size());
		for(Element aElement : aElements) {
			String uri = aElement.attr("href");
			int sIdx = uri.indexOf("aid=");
			String aid = uri.substring(sIdx);
			if(aid.indexOf("&") != -1) {
				aid = aid.substring(0,aid.indexOf("&"));
			}
			aid = aid.replace("aid=", "");
			
			String title = aElement.text();
			NaverITNews nitn = new NaverITNews();
			nitn.setUri(uri);
			nitn.setAid(aid);
			nitn.setTitle(title);
			nitnList.add(nitn);
		}
		
			nitnRepo.saveAll(nitnList);
	}
	
	

}
