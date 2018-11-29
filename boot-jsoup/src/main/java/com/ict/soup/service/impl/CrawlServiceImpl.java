package com.ict.soup.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ict.soup.collection.DaumIssue;
import com.ict.soup.repository.DaumIssueRepository;
import com.ict.soup.service.CrawlService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrawlServiceImpl implements CrawlService{
	
	@Value("${ex.target.url}")
	private String targetUrl;
	
	@Value("${ex.parent.selector}")
	private String pSelector;
	
	@Value("${ex.dt.selector}")
	private String dtSelector;
		
	@Autowired
	private DaumIssueRepository diRepo;

	@Override
	public List<DaumIssue> getDaumIssue() throws IOException {
		Document doc = Jsoup.connect(targetUrl).get();
		Elements ulElements = doc.select(pSelector);
		Elements aElements = ulElements.select(dtSelector);
		List<DaumIssue> diList = new ArrayList<>();
		
		for(Element element : aElements) {
			DaumIssue daumIssue = new DaumIssue();
			daumIssue.setUri(element.attr("href"));
			daumIssue.setText(element.text());
			diList.add(daumIssue);
		}
		return diRepo.saveAll(diList);
	}
	
	
}
